package com.senla.hotel.dao;

import com.senla.hotel.entity.Booking;

import java.util.List;

public interface IBookingDAO {
    List<Booking> getBookings();
}
