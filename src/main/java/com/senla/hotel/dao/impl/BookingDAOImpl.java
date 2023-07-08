package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.EntityDAO;
import com.senla.hotel.dao.IBookingDAO;
import com.senla.hotel.entity.Booking;

import java.util.*;

public class BookingDAOImpl extends EntityDAO implements IBookingDAO {

    private static final BookingDAOImpl INSTANCE = new BookingDAOImpl();
    private final Map<Long, Booking> bookings = new HashMap<>();
    private static Set<Long> idHolder = new HashSet<>();

    public static BookingDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Booking> getAll() {
        return new ArrayList<>(bookings.values());
    }

    public void setBookings(List<Booking> bookings) {
        bookings.forEach(this::save);
    }

    @Override
    public Booking getById(long bookingId) {
        return bookings.get(bookingId);
    }

    @Override
    public void save(Booking booking) {
        this.bookings.put(generateId(idHolder), booking);
    }

    @Override
    public Booking getByGuestId(long guestId) {
        return bookings.values().stream()
                .filter(b -> b.getGuestId() == guestId)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("There is no booking for such a guest with id->" + guestId));
    }


}
