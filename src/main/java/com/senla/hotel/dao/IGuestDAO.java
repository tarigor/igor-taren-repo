package com.senla.hotel.dao;

import com.senla.hotel.entity.Guest;

import java.util.List;

public interface IGuestDAO {
    List<Guest> getAll();

    Guest getById(long guestId);

    void save(Guest guest);
}
