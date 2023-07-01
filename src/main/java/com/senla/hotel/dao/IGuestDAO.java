package com.senla.hotel.dao;

import com.senla.hotel.entity.Guest;

import java.util.List;

public interface IGuestDAO {
    List<Guest> getGuests();

    Guest getGuestById(long guestId);
    void save(Guest guest);
}
