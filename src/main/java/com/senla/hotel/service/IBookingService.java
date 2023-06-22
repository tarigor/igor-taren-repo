package com.senla.hotel.service;

import com.senla.hotel.entity.Booking;

import java.util.List;

public interface IBookingService {
    //    List of guests and their rooms (sort alphabetically and by check-out date);
    List<Booking> findAllSortByAlphabetically();
    List<Booking> findAllSortByCheckOutDate();
    //    View the last 3 guests of the room and the dates of their stay;
    List<Booking> findLastGuestOfRoomAndDates(int countOfGuests, long roomId);
    //    The amount of payment for the room to be paid by the guest;
    double getTotalPaymentByGuest(long guestId);
}
