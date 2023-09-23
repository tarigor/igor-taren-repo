package com.senla.hotel.service.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.impl.BookingDAOImpl;
import com.senla.hotel.dao.impl.GuestDAOImpl;
import com.senla.hotel.dao.impl.RoomDAOImpl;
import com.senla.hotel.dto.GuestBookingDTO;
import com.senla.hotel.entity.Booking;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.entity.Room;
import com.senla.hotel.service.CommonService;
import com.senla.hotel.service.IBookingService;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@CreateInstanceAndPutInContainer
public class BookingServiceImpl extends CommonService implements IBookingService {
    private static final Set<Long> idHolder = new HashSet<>();
    private BookingDAOImpl bookingDAO;

    @InjectValue(key = "BookingDAOImpl")
    public void setBookingDAO(BookingDAOImpl bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    private RoomDAOImpl roomDAO;

    @InjectValue(key = " RoomDAOImpl")
    public void setRoomDAO(RoomDAOImpl roomDAO) {
        this.roomDAO = roomDAO;
    }

    private GuestDAOImpl guestDAO;

    @InjectValue(key = "GuestDAOImpl")
    public void setGuestDAO(GuestDAOImpl guestDAO) {
        this.guestDAO = guestDAO;
    }

    @Override
    public void saveAll(List<Booking> bookings) {
        for (Booking booking : bookings) {
            setId(booking);
        }
        bookingDAO.saveAll(bookings);
    }

    //    List of guests and their rooms (sort alphabetically and by check-out date);
    @Override
    public List<GuestBookingDTO> findAllOrderedAlphabetically() {
        List<Guest> guests = guestDAO.getAll();
        return bookingDAO.getAll().stream()
                .map(b -> new GuestBookingDTO(guests.stream()
                        .filter(g -> g.getId() == b.getGuestId())
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("There is no results of requested condition")), b))
                .sorted(Comparator.comparing(g -> g.getGuest().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllOrderedByCheckOutDate() {
        return bookingDAO.getAll().stream()
                .sorted(Comparator.comparing(Booking::getCheckOutDate))
                .collect(Collectors.toList());
    }

    //    View the last 3 guests of the room and the dates of their stay;
    @Override
    public List<Booking> findLastGuestOfRoomAndDates(int countOfGuests, long roomId) {
        return bookingDAO.getAll().stream()
                .sorted(Comparator.comparing(Booking::getCheckOutDate).reversed())
                .limit(countOfGuests)
                .collect(Collectors.toList());
    }

    //    The amount of payment for the room to be paid by the guest;
    @Override
    public double getTotalPaymentByGuest(long guestId) {
        Booking booking = getByGuestId(guestId);
        long bookedDays = Duration.between(booking.getCheckInDate().toInstant(), booking.getCheckOutDate().toInstant())
                .toDays();
        return roomDAO.getById(booking.getBookedRoomId()).getPrice() * bookedDays;
    }

    //    List of rooms that will be available on a certain date in the future;
    @Override
    public List<Room> findAvailableRoomsByDate(Date date) {
        return bookingDAO.getAll().stream()
                .filter(b -> ((b.getCheckInDate().after(date) && b.getCheckOutDate().after(date)) ||
                        (b.getCheckInDate().before(date) && b.getCheckOutDate().before(date))))
                .map(b -> roomDAO.getById(b.getBookedRoomId()))
                .collect(Collectors.toList());
    }


    @Override
    public long findCountOfAllGuests() {
        Date currentDate = new Date();
        return bookingDAO.getAll().stream()
                .filter(b -> b.getCheckInDate().before(currentDate) && b.getCheckOutDate().after(currentDate))
                .count();
    }

    @Override
    public Booking getByGuestId(long guestId) {
        return bookingDAO.getAll().stream()
                .filter(b -> b.getGuestId() == guestId)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("There is no booking for such a guest with id->" + guestId));
    }

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<Booking> bookings) {
        for (Booking booking : bookings) {
            if (bookingDAO.getById(booking.getId()) != null) {
                bookingDAO.update(booking);
            } else {
                setId(booking);
                bookingDAO.save(booking);
            }
        }
    }

    @Override
    public List<Booking> getAll() {
        return bookingDAO.getAll();
    }

    private void setId(Booking booking) {
        if (booking.getId() == 0) {
            booking.setId(generateId(idHolder));
        }
    }
}
