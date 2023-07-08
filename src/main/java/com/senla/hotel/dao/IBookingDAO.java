package com.senla.hotel.dao;

import com.senla.hotel.entity.Booking;

import java.util.List;

public interface IBookingDAO {
    List<Booking> getAll();

    Booking getById(long bookingId);

    void save(Booking booking);

    Booking getByGuestId(long guestId);
}
