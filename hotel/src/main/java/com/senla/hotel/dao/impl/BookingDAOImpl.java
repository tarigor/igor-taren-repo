package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class BookingDAOImpl implements IEntityDAO<Booking> {

    private Map<Long, Booking> bookings = new HashMap<>();

    public Map<Long, Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Map<Long, Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public List<Booking> getAll() {
        return new ArrayList<>(bookings.values());
    }

    @Override
    public void saveAll(List<Booking> bookings) {
        bookings.forEach(this::save);
    }

    @Override
    public Booking getById(long bookingId) {
        return bookings.get(bookingId);
    }

    @Override
    public void save(Booking booking) {
        this.bookings.put(booking.getId(), booking);
    }

    @Override
    public Booking update(Booking booking) {
        Booking updatedBooking = getById(booking.getId());
        updatedBooking.setGuestId(booking.getGuestId());
        updatedBooking.setGuestServicesId(booking.getGuestServicesId());
        updatedBooking.setBookedRoomId(booking.getBookedRoomId());
        updatedBooking.setCheckInDate(booking.getCheckInDate());
        updatedBooking.setCheckOutDate(booking.getCheckOutDate());
        return updatedBooking;
    }
}
