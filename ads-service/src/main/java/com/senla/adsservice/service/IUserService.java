package com.senla.adsservice.service;

import com.senla.adsdatabase.entity.User;
import com.senla.adsservice.dto.AuthenticationRequestDto;
import com.senla.adsservice.dto.AuthenticationResponseDto;
import com.senla.adsservice.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto userModify(UserDto userDto);

    UserDto getUser(long id);

    List<UserDto> findSellers();

    List<UserDto> findBuyers();

    UserDto userRegister(UserDto userDto);

    AuthenticationResponseDto userLogin(AuthenticationRequestDto authenticationRequestDto);

    User findUserByEmail(String email);
}
