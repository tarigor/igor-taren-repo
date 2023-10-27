package com.senla.hotel.service.impl;

import com.senla.hotel.dto.GuestBookingDto;
import com.senla.hotel.service.IBookingService;
import com.senla.hoteldb.dao.impl.BookingDao;
import com.senla.hoteldb.dao.impl.GuestDao;
import com.senla.hoteldb.dao.impl.RoomDao;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.Room;
import com.senla.hoteldb.service.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements IBookingService {
    //    @Value("${number-of-guest-records-in-room-history}")
    private Integer roomHistoryLimit;
    private BookingDao bookingDAO;
    private RoomDao roomDAO;
    private GuestDao guestDAO;
    private HibernateService hibernateService;

    @Autowired
    public void setBookingDAO(BookingDao bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @Autowired
    public void setRoomDAO(RoomDao roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Autowired
    public void setGuestDAO(GuestDao guestDAO) {
        this.guestDAO = guestDAO;
    }

    @Autowired
    public void setHibernateConfig(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @Override
    public void saveAll(List<Booking> bookings) {
        bookingDAO.saveAll(bookings);
    }

    //    List of guests and their rooms (sort alphabetically and by check-out date);
    @Override
    public List<GuestBookingDto> findAllOrderedAlphabetically() {
        hibernateService.beginTransaction();
        List<Guest> guests = guestDAO.getAll();
        List<Booking> bookings = bookingDAO.getAll();
        List<GuestBookingDto> result = bookings.stream()
                .map(b -> new GuestBookingDto(guests.stream()
                        .filter(g -> g.getId() == b.getGuest().getId())
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("There is no result for the requested condition")), b))
                .sorted(Comparator.comparing(g -> g.getGuest().getLastName()))
                .limit(roomHistoryLimit)
                .collect(Collectors.toList());
        hibernateService.commit();
        return result;
    }

    @Override
    public List<Booking> findAllOrderedByCheckOutDate() {
        return bookingDAO.getAll().stream()
                .sorted(Comparator.comparing(Booking::getCheckOutDate))
                .limit(roomHistoryLimit)
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
        Duration duration = Duration.between(new Date(booking.getCheckInDate().getTime()).toInstant(), new Date(booking.getCheckOutDate().getTime()).toInstant());
        return roomDAO.getById(booking.getRoom().getId()).getPrice() * duration.toDays();
    }

    //    List of rooms that will be available on a certain date in the future;
    @Override
    public List<Room> findAvailableRoomsByDate(Date date) {
        hibernateService.beginTransaction();
        List<Booking> bookings = bookingDAO.getAll();
        List<Room> availableRooms = bookings.stream()
                .filter(b -> ((b.getCheckInDate().after(date) && b.getCheckOutDate().after(date)) ||
                        (b.getCheckInDate().before(date) && b.getCheckOutDate().before(date))))
                .map(b -> roomDAO.getById(b.getRoom().getId()))
                .collect(Collectors.toList());
        hibernateService.commit();
        return availableRooms;
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
                .filter(b -> b.getGuest().getId() == guestId)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("There is no booking for such a guest with id->" + guestId));
    }

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<Booking> bookings) {
        for (Booking booking : bookings) {
            if (bookingDAO.getById(booking.getId()) != null) {
                bookingDAO.update(booking);
            } else {
                bookingDAO.save(booking);
            }
        }
    }

    @Override
    public List<Booking> getAll() {
        return bookingDAO.getAll();
    }
}
