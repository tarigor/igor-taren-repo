package com.senla.hotel.service;

import com.senla.hotel.dto.BookingDto;
import com.senla.hotel.dto.GuestBookingDto;
import com.senla.hotel.dto.RoomDto;
import com.senla.hoteldb.entity.Booking;

import java.util.ArrayList;
import java.util.List;

public interface IBookingService {

    void saveAll(List<Booking> bookings);

    //    List of guests and their rooms (sort alphabetically and by check-out date);
    List<GuestBookingDto> findAllOrderedAlphabetically();

    List<BookingDto> findAllOrderedByCheckOutDate();

    //    View the last 3 guests of the room and the dates of their stay;
    List<BookingDto> findLastGuestOfRoomAndDates(int countOfGuests, long roomId);

    //    The amount of payment for the room to be paid by the guest;
    double getTotalPaymentByGuest(long guestId);

    //    List of rooms that will be available on a certain date in the future;
    List<RoomDto> findAvailableRoomsByDate(String date);

    //    Total number of guests;
    long findCountOfAllGuests(String dateString);

    Booking getByGuestId(long guestId);

    void updateAllAndSaveIfNotExist(ArrayList<Booking> bookings);

    List<Booking> getAll();
}
