package com.senla.adsservice.service.impl;

import com.senla.adsservice.dto.UserDto;
import com.senla.adsservice.service.IUserService;
import com.senla.adsservice.util.EntityDtoMapper;
import com.senla.database.entity.User;
import com.senla.database.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.senla.adsservice.enums.UserRole.BUYER;
import static com.senla.adsservice.enums.UserRole.SELLER;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final EntityDtoMapper entityDtoMapper;

    public UserServiceImpl(UserRepository userRepository, EntityDtoMapper entityDtoMapper) {
        this.userRepository = userRepository;
        this.entityDtoMapper = entityDtoMapper;
    }

    @Override
    public UserDto userModify(UserDto userDto) {
        UserDto userFromDB = getUser(userDto.getId());
        userFromDB.setFirstName(userDto.getFirstName());
        userFromDB.setLastName(userDto.getLastName());
        userFromDB.setEmail(userFromDB.getEmail());
        userFromDB.setUserType(userDto.getUserType());
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
                .filter(u -> u.getUserType().equals(SELLER.name()))
                .map(user -> entityDtoMapper.convertFromEntityToDto(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findBuyers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(u -> u.getUserType().equals(BUYER.name()))
                .map(user -> entityDtoMapper.convertFromEntityToDto(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
