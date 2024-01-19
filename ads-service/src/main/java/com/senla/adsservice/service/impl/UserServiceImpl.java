package com.senla.adsservice.service.impl;

import com.senla.adsdatabase.entity.User;
import com.senla.adsdatabase.repository.UserRepository;
import com.senla.adsservice.dto.UserDto;
import com.senla.adsservice.service.IUserService;
import com.senla.adsservice.util.EntityDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public UserServiceImpl(UserRepository userRepository, EntityDtoMapper entityDtoMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.entityDtoMapper = entityDtoMapper;
        this.passwordEncoder = passwordEncoder;
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
}
