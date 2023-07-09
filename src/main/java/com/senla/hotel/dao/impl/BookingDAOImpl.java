package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.EntityDAO;
import com.senla.hotel.dao.IBookingDAO;
import com.senla.hotel.entity.Booking;

import java.util.*;

public class BookingDAOImpl extends EntityDAO implements IBookingDAO {

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

    public void saveAll(List<Booking> bookings) {
        bookings.forEach(this::save);
    }

    @Override
    public Booking getById(long bookingId) {
        return bookings.get(bookingId);
    }

    @Override
    public void save(Booking booking) {
        long id = generateId(idHolder);
        booking.setId(id);
        this.bookings.put(id, booking);
    }
}
