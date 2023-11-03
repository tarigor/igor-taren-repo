package com.senla.hotelweb.controller;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomOperation;
import com.senla.hotel.constant.RoomSection;
import com.senla.hotel.dto.RoomDto;
import com.senla.hotel.dto.searchcriteria.RoomDetailsSearchCriteria;
import com.senla.hotel.dto.searchcriteria.RoomSearchCriteria;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.validator.annotation.EnumValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "rooms")
@Validated
public class RoomController {

    private final RoomServiceImpl roomService;

    @Autowired
    public RoomController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    //6=Total number of available rooms
    @GetMapping("/available")
    public Integer getTotalAvailableRooms() {
        return roomService.getTotalAvailableRooms();
    }

    //1=List of rooms sorted by price
    //2=List of rooms sorted by capacity
    //3=List of rooms sorted by number of stars
    //API->/rooms?getOnlyAvailable=false&sortBy=PRICE&sortOrder=DESC
    @GetMapping("/sort")
    public List<RoomDto> getSortedRooms(@Valid RoomSearchCriteria roomSearchCriteria) {
        boolean getOnlyAvailable = Boolean.parseBoolean(roomSearchCriteria.getGetOnlyAvailable());
        String sortBy = roomSearchCriteria.getSortBy();
        String sortOrder = roomSearchCriteria.getSortOrder();
        if (getOnlyAvailable) {
            return roomService.getAvailableSortedRooms(sortBy, sortOrder);
        }
        return roomService.getSortedRooms(sortBy);
    }

    //12=Prices of services and rooms (sort by section(category), by price);
    @GetMapping("/prices")
    public List<RoomDto> getAllOrdered(@Valid RoomDetailsSearchCriteria roomDetailsSearchCriteria) {
        RoomSection roomSection = RoomSection.valueOf(roomDetailsSearchCriteria.getSortBy());
        Ordering ordering = Ordering.valueOf(roomDetailsSearchCriteria.getSortOrder());
        return roomService.getAllOrdered(roomSection, ordering);
    }

    //14=Show the details of a separate room
    @PostMapping
    public RoomDto saveRoom(@Valid @RequestBody RoomDto roomDto) {
        return roomService.addRoom(roomDto);
    }

    //19=Do check-in/check-out from the room
    @GetMapping("/operation/{operation}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void roomRegister(@PathVariable @EnumValidator(targetClassType = RoomOperation.class) String operation, @PathVariable Long id) {
        roomService.roomRegister(operation, id);
    }

    //  all other CRUD operations added by spring-boot-starter-data-rest
}
