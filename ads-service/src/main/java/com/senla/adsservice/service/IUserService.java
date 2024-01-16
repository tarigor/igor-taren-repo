package com.senla.adsservice.service;

import com.senla.database.entity.User;

import java.util.List;

public interface IUserService {
    User userRegister(User user);

    User userModify(User user);

    User getUser(int id);

    List<User> getSellers();

    List<User> getBuyers();
}
