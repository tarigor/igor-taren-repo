package com.senla.hotel.dao;

import com.senla.hotel.entity.Booking;

import java.util.List;

public interface IBookingDAO {
    List<Booking> getBookings();

    Booking getBookingById(long bookingId);

    void save(Booking booking);

    Booking getBookingByGuestId(long guestId);
}
