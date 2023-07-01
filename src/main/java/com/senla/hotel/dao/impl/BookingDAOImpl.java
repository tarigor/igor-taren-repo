package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IBookingDAO;
import com.senla.hotel.entity.Booking;

import java.util.*;

public class BookingDAOImpl implements IBookingDAO {

    private static final BookingDAOImpl INSTANCE = new BookingDAOImpl();
    private Map<Long, Booking> bookings = new HashMap<>();
    ;

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
        this.bookings.put(booking.getId(), booking);
    }

    @Override
    public Booking getBookingByGuestId(long guestId) {
        return Optional.of(bookings.values().stream().filter(b -> b.getGuestId() == guestId).findFirst()).get()
                .orElseThrow(() -> new NoSuchElementException("There is no booking for such a guest with id->" + guestId));
    }


}
