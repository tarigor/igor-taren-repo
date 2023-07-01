package com.senla.hotel.service;

import com.senla.hotel.dto.GuestBookingDTO;
import com.senla.hotel.entity.Booking;
import com.senla.hotel.entity.Room;

import java.util.Date;
import java.util.List;

public interface IBookingService {
    //    List of guests and their rooms (sort alphabetically and by check-out date);
    List<GuestBookingDTO> findAllOrderedAlphabetically();

    List<Booking> findAllOrderedByCheckOutDate();

    //    View the last 3 guests of the room and the dates of their stay;
    List<Booking> findLastGuestOfRoomAndDates(int countOfGuests, long roomId);

    //    The amount of payment for the room to be paid by the guest;
    double getTotalPaymentByGuest(long guestId);

    //    List of rooms that will be available on a certain date in the future;
    List<Room> findAvailableRoomsByDate(Date date);

    //    Total number of guests;
    long findCountOfAllGuests();
}
