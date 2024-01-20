package com.senla.adsservice.service.impl;

import com.senla.adsdatabase.entity.User;
import com.senla.adsdatabase.repository.UserRepository;
import com.senla.adssecurity.service.JwtTokenService;
import com.senla.adssecurity.service.JwtUserDetailsService;
import com.senla.adsservice.dto.AuthenticationRequestDto;
import com.senla.adsservice.dto.AuthenticationResponseDto;
import com.senla.adsservice.dto.UserDto;
import com.senla.adsservice.service.IUserService;
import com.senla.adsservice.util.EntityDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.senla.adsservice.enums.UserRole.BUYER;
import static com.senla.adsservice.enums.UserRole.SELLER;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final EntityDtoMapper entityDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenService jwtTokenService;

    public UserServiceImpl(UserRepository userRepository,
                           EntityDtoMapper entityDtoMapper,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtUserDetailsService jwtUserDetailsService,
                           JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.entityDtoMapper = entityDtoMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public UserDto userModify(UserDto userDto) {
        UserDto userFromDB = getUser(userDto.getId());
        userFromDB.setFirstName(userDto.getFirstName());
        userFromDB.setLastName(userDto.getLastName());
        userFromDB.setEmail(userFromDB.getEmail());
        userFromDB.setUserRole(userDto.getUserRole());
        userFromDB.setPassword(userFromDB.getPassword());
        User userSaveToDb = entityDtoMapper.convertFromDtoToEntity(userFromDB, User.class);
        return entityDtoMapper.convertFromEntityToDto(userSaveToDb, UserDto.class);
    }

    @Override
    public UserDto getUser(long id) {
        return entityDtoMapper.convertFromEntityToDto(userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("there is no such a user with id->" + id)),
                UserDto.class);
    }

    @Override
    public List<UserDto> findSellers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(u -> u.getUserRole().equals(SELLER.name()))
                .map(user -> entityDtoMapper.convertFromEntityToDto(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findBuyers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(u -> u.getUserRole().equals(BUYER.name()))
                .map(user -> entityDtoMapper.convertFromEntityToDto(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto userRegister(UserDto userDto) {
        List<User> users = userRepository.findAll();
        if (users.stream().anyMatch(u -> u.getEmail().equals(userDto.getEmail()))) {
            throw new InvalidParameterException("There is an account with that email address: "
                    + userDto.getEmail());
        }
        User newUser = new User();
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setUserRole(userDto.getUserRole());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return entityDtoMapper.convertFromEntityToDto(userRepository.save(newUser), UserDto.class);
    }

    @Override
    public AuthenticationResponseDto userLogin(AuthenticationRequestDto authenticationRequestDto) {
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
}
