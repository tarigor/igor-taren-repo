package com.senla.hotelweb.controller;

import com.senla.hotel.dto.BookingDto;
import com.senla.hotel.dto.GuestBookingDto;
import com.senla.hotel.dto.RoomDto;
import com.senla.hotel.service.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingServiceImpl bookingService;

    //4=List of guests and their rooms sorted alphabetically
    @GetMapping("/guests/rooms/alphabet")
    public List<GuestBookingDto> findAllOrderedAlphabetically() {
        return bookingService.findAllOrderedAlphabetically();
    }

    //5=List of guests and their rooms sorted by check-out date
    @GetMapping("/guests/rooms/checkout")
    public List<BookingDto> findAllOrderedByCheckOutDate() {
        return bookingService.findAllOrderedByCheckOutDate();
    }

    //7=Total number of guests
    @GetMapping("/guests/total")
    public long findCountOfAllGuests() {
        return bookingService.findCountOfAllGuests();
    }

    //8=List of rooms that will be available on a certain date in the future
    @GetMapping("/rooms/{dateString}")
    public List<RoomDto> findAvailableRoomsByDate(@PathVariable String dateString) {
        return bookingService.findAvailableRoomsByDate(dateString);
    }

    //9=The amount of payment for the room to be paid by the guest
    @GetMapping("/room/payment/byGuestId/{id}")
    public double getTotalPaymentByGuest(@PathVariable long id) {
        return bookingService.getTotalPaymentByGuest(id);
    }

    //10=View the last 3 guests of the room and the dates of their stay
    @GetMapping("/last/guestAmount/{amount}/room/{id}")
    public List<BookingDto> findLastGuestOfRoomAndDates(@PathVariable int amount, @PathVariable long id) {
        return bookingService.findLastGuestOfRoomAndDates(amount, id);
    }
}
