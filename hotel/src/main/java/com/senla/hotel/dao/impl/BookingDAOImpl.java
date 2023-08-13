package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.Booking;

import java.util.*;

public class BookingDAOImpl implements IEntityDAO<Booking> {

    private static final BookingDAOImpl INSTANCE = new BookingDAOImpl();
    private static final Set<Long> idHolder = new HashSet<>();
    private final Map<Long, Booking> bookings = new HashMap<>();

    public static BookingDAOImpl getInstance() {
        return INSTANCE;
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
        long id;
        if (booking.getId() != 0) {
            id = booking.getId();
        } else {
            id = generateId(idHolder);
            booking.setId(id);
        }
        this.bookings.put(id, booking);
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
