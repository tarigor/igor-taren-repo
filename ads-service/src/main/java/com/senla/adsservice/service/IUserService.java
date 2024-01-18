package com.senla.adsservice.service;

import com.senla.adsservice.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto userModify(UserDto userDto);

    UserDto getUser(long id);

    List<UserDto> findSellers();

    List<UserDto> findBuyers();
}
