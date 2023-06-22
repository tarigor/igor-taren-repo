package com.senla.hotel.service.impl;

import com.senla.hotel.dao.IBookingDAO;
import com.senla.hotel.entity.Booking;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.service.IBookingService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements IBookingService {
    private IBookingDAO bookingDAO;

    public void setBookingDAO(IBookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @Override
    public List<Booking> findAllSortByAlphabetically() {
        return bookingDAO.getBookings().stream()
                .map(b -> new Booking(b.getBookingId(), b.getGuest(), b.getBookedRoomId()))
                .sorted(Comparator.comparing(b -> b.getGuest().getFirstName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllSortByCheckOutDate() {
        return null;
    }
    @Override
    public List<Booking> findLastGuestOfRoomAndDates(int countOfGuests, long roomId) {
        return null;
    }

    @Override
    public double getTotalPaymentByGuest(long guestId) {
        return 0;
    }
}
