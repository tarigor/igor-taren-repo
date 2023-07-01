package com.senla.hotel.service.impl;

import com.senla.hotel.dao.IBookingDAO;
import com.senla.hotel.dao.IGuestDAO;
import com.senla.hotel.dao.IRoomDAO;
import com.senla.hotel.dto.GuestBookingDTO;
import com.senla.hotel.entity.Booking;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.entity.Room;
import com.senla.hotel.service.IBookingService;
import org.apache.commons.lang3.time.DateUtils;

import java.time.Duration;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements IBookingService {
    private static final BookingServiceImpl INSTANCE = new BookingServiceImpl();
    private IBookingDAO bookingDAO;
    private IRoomDAO roomDAO;
    private IGuestDAO guestDAO;

    public static BookingServiceImpl getInstance() {
        return INSTANCE;
    }

    public void setRoomDAO(IRoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public void setGuestDAO(IGuestDAO guestDAO) {
        this.guestDAO = guestDAO;
    }

    public void setBookingDAO(IBookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    //    List of guests and their rooms (sort alphabetically and by check-out date);
    @Override
    public List<GuestBookingDTO> findAllOrderedAlphabetically() {
        List<Guest> guests = guestDAO.getGuests();
        return bookingDAO.getBookings().stream()
                .map(b -> new GuestBookingDTO(guests.stream().filter(g -> g.getId() == b.getGuestId()).findFirst().get(), b))
                .sorted(Comparator.comparing(g -> g.getGuest().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllOrderedByCheckOutDate() {
        return bookingDAO.getBookings().stream()
                .sorted(Comparator.comparing(Booking::getCheckOutDate))
                .collect(Collectors.toList());
    }

    //    View the last 3 guests of the room and the dates of their stay;
    @Override
    public List<Booking> findLastGuestOfRoomAndDates(int countOfGuests, long roomId) {
        return bookingDAO.getBookings().stream()
                .sorted(Comparator.comparing(Booking::getCheckOutDate).reversed())
                .limit(countOfGuests)
                .collect(Collectors.toList());
    }

    //    The amount of payment for the room to be paid by the guest;
    @Override
    public double getTotalPaymentByGuest(long guestId) {
        Booking booking = bookingDAO.getBookingByGuestId(guestId);
        long bookedDays = Duration.between(
                booking.getCheckInDate().toInstant(),
                booking.getCheckOutDate().toInstant()).toDays();
        return roomDAO.getRoom(booking.getBookedRoomId()).getPrice() * bookedDays;
    }

    //    List of rooms that will be available on a certain date in the future;
    @Override
    public List<Room> findAvailableRoomsByDate(Date date) {

        return bookingDAO.getBookings().stream()
                .filter(b -> b.getCheckInDate().after(date) || b.getCheckOutDate().before(date))
                .map(Booking::getBookedRoomId)
                .map(i -> roomDAO.getRoom(i))
                .collect(Collectors.toList());
    }


    @Override
    public long findCountOfAllGuests() {
        Date currentDate = new Date();
        return bookingDAO.getBookings().stream()
                .filter(b -> b.getCheckInDate().before(currentDate) && b.getCheckOutDate().after(currentDate))
                .count();
    }

}
