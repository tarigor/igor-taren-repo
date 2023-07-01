package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IBookingDAO;
import com.senla.hotel.entity.Booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingDAOImpl implements IBookingDAO {

    private static final BookingDAOImpl INSTANCE = new BookingDAOImpl();
    private Map<Long, Booking> bookings = new HashMap<>();;

    public static BookingDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings.values());
    }

    public void setBookings(List<Booking> bookings) {
        bookings.forEach(this::save);
    }

    @Override
    public Booking getBookingById(long bookingId) {
        return bookings.get(bookingId);
    }

    @Override
    public void save(Booking booking) {
        this.bookings.put(booking.getBookingId(), booking);
    }


}
