package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IBookingDAO;
import com.senla.hotel.entity.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookingDAOImpl implements IBookingDAO {

    private static final BookingDAOImpl INSTANCE = new BookingDAOImpl();
    Map<Long, Booking> bookings;

    public static BookingDAOImpl getInstance() {
        return INSTANCE;
    }

    public void setBookings(Map<Long, Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings.values());
    }

    @Override
    public Booking getBookingById(long bookingId) {
        return bookings.get(bookingId);
    }


}
