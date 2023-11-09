package com.senla.hotelweb.controller;

import com.senla.hotel.dto.RoomDto;
import com.senla.hotel.dto.searchcriteria.RoomDetailsSearchCriteria;
import com.senla.hotel.dto.searchcriteria.RoomSearchCriteria;
import com.senla.hotel.dto.searchcriteria.RoomServiceSearchCriteria;
import com.senla.hotel.enums.Ordering;
import com.senla.hotel.enums.RoomSection;
import com.senla.hotel.enums.RoomServiceSection;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hoteldb.entity.RoomService;
import com.senla.hotelsecurity.dto.AuthenticationRequestDto;
import com.senla.hotelsecurity.dto.AuthenticationResponseDto;
import com.senla.hotelsecurity.service.JwtTokenService;
import com.senla.hotelsecurity.service.JwtUserDetailsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/any")
@Slf4j
public class AnyController {
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService jwtUserDetailsService;
    private JwtTokenService jwtTokenService;
    private RoomServiceImpl roomService;
    private BookingServiceImpl bookingService;
    private RoomServicesServiceImpl roomServicesService;

    @Autowired
    public void setRoomServicesService(RoomServicesServiceImpl roomServicesService) {
        this.roomServicesService = roomServicesService;
    }

    @Autowired
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @Autowired
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Autowired
    public void setJwtTokenService(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Autowired
    public void setJwtUserDetailsService(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Autowired

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public AuthenticationResponseDto authenticate(@RequestBody @Valid final AuthenticationRequestDto authenticationRequestDto) {
        try {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(authenticationRequestDto.getLogin(), authenticationRequestDto.getPassword());
            authenticationManager.authenticate(authRequest);
        } catch (final BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequestDto.getLogin());
        final AuthenticationResponseDto authenticationResponse = new AuthenticationResponseDto();
        String token = jwtTokenService.generateToken(userDetails);
        authenticationResponse.setAccessToken(token);
        authenticationResponse.setExpirationTime(jwtTokenService.getExpirationTime(token));
        log.info("user token -> {} valid -> {}", authenticationResponse.getAccessToken(), authenticationResponse.getExpirationTime());
        return authenticationResponse;
    }

    //1=List of rooms sorted by price
    //2=List of rooms sorted by capacity
    //3=List of rooms sorted by number of stars
    //API->/rooms?getOnlyAvailable=false&sortBy=PRICE&sortOrder=DESC
    @GetMapping("/rooms/sort")
    public List<RoomDto> getSortedRooms(@Valid RoomSearchCriteria roomSearchCriteria) {
        boolean getOnlyAvailable = Boolean.parseBoolean(roomSearchCriteria.getGetOnlyAvailable());
        String sortBy = roomSearchCriteria.getSortBy();
        String sortOrder = roomSearchCriteria.getSortOrder();
        if (getOnlyAvailable) {
            return roomService.getAvailableSortedRooms(sortBy, sortOrder);
        }
        return roomService.getSortedRooms(sortBy);
    }

    //8=List of rooms that will be available on a certain date in the future
    @GetMapping("/rooms/{dateString}")
    public List<RoomDto> findAvailableRoomsByDate(@PathVariable String dateString) {
        return bookingService.findAvailableRoomsByDate(dateString);
    }

    //12=Prices of services and rooms (sort by section(category), by price);
    @GetMapping("/rooms/prices")
    public List<RoomDto> getAllOrdered(@Valid RoomDetailsSearchCriteria roomDetailsSearchCriteria) {
        RoomSection roomSection = RoomSection.valueOf(roomDetailsSearchCriteria.getSortBy());
        Ordering ordering = Ordering.valueOf(roomDetailsSearchCriteria.getSortOrder());
        return roomService.getAllOrdered(roomSection, ordering);
    }

    //13=Room services (ordered by ROME_SERVICES,PRICE) in ASC(DESC) manner
    @GetMapping("/rooms/services")
    public List<RoomService> getAllOrdered(@Valid RoomServiceSearchCriteria roomServiceSearchCriteria) {
        RoomServiceSection roomServiceSection = RoomServiceSection.valueOf(roomServiceSearchCriteria.getSortBy());
        Ordering ordering = Ordering.valueOf(roomServiceSearchCriteria.getSortOrder());
        return roomServicesService.getAllOrdered(roomServiceSection, ordering);
    }
}
