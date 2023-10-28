package com.senla.hotel.service.impl;

import com.senla.hotel.dto.GuestBookingDto;
import com.senla.hotel.service.IBookingService;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.Room;
import com.senla.hoteldb.repository.BookingRepository;
import com.senla.hoteldb.repository.GuestRepository;
import com.senla.hoteldb.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements IBookingService {
    @Value("${number-of-guest-records-in-room-history}")
    private Integer roomHistoryLimit;
    private BookingRepository bookingRepository;
    private RoomRepository roomRepository;
    private GuestRepository guestRepository;

    @Autowired
    public void setBookingRepository(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Autowired
    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Autowired
    public void setGuestRepository(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public void saveAll(List<Booking> bookings) {
        bookingRepository.saveAll(bookings);
    }

    //    List of guests and their rooms (sort alphabetically and by check-out date);
    @Transactional
    @Override
    public List<GuestBookingDto> findAllOrderedAlphabetically() {
        List<Guest> guests = guestRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(b -> new GuestBookingDto(guests.stream()
                        .filter(g -> Objects.equals(g.getId(), b.getGuest().getId()))
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("There is no result for the requested condition")), b))
                .sorted(Comparator.comparing(g -> g.getGuest().getLastName()))
                .limit(roomHistoryLimit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findAllOrderedByCheckOutDate() {
        return bookingRepository.findAll().stream()
                .sorted(Comparator.comparing(Booking::getCheckOutDate))
                .limit(roomHistoryLimit)
                .collect(Collectors.toList());
    }

    //    View the last 3 guests of the room and the dates of their stay;
    @Override
    public List<Booking> findLastGuestOfRoomAndDates(int countOfGuests, long roomId) {
        return bookingRepository.findAll().stream()
                .sorted(Comparator.comparing(Booking::getCheckOutDate).reversed())
                .limit(countOfGuests)
                .collect(Collectors.toList());
    }

    //    The amount of payment for the room to be paid by the guest;
    @Override
    public double getTotalPaymentByGuest(long guestId) {
        Booking booking = getByGuestId(guestId);
        Duration duration = Duration.between(new Date(booking.getCheckInDate().getTime()).toInstant(), new Date(booking.getCheckOutDate().getTime()).toInstant());
        return roomRepository.findById(booking.getRoom().getId()).orElseThrow(() -> new NoSuchElementException("There is no such a booking with id->" + booking.getRoom().getId())).getPrice() * duration.toDays();
    }

    //    List of rooms that will be available on a certain date in the future;
    @Transactional
    @Override
    public List<Room> findAvailableRoomsByDate(Date date) {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .filter(b -> ((b.getCheckInDate().after(date) && b.getCheckOutDate().after(date)) ||
                        (b.getCheckInDate().before(date) && b.getCheckOutDate().before(date))))
                .map(b -> roomRepository.findById(b.getRoom().getId()).orElseThrow(() -> new NoSuchElementException("There is no such a room with id->" + b.getRoom().getId())))
                .collect(Collectors.toList());
    }

    @Override
    public long findCountOfAllGuests() {
        Date currentDate = new Date();
        return bookingRepository.findAll().stream()
                .filter(b -> b.getCheckInDate().before(currentDate) && b.getCheckOutDate().after(currentDate))
                .count();
    }

    @Override
    public Booking getByGuestId(long guestId) {
        return bookingRepository.findAll().stream()
                .filter(b -> b.getGuest().getId() == guestId)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("There is no booking for such a guest with id->" + guestId));
    }

    @Override
    public void updateAllAndSaveIfNotExist(ArrayList<Booking> bookings) {
        for (Booking booking : bookings) {
            Optional<Booking> bookingOptional = bookingRepository.findById(booking.getId());
            if (bookingOptional.isPresent()) {
                Booking bookingUpdate = bookingOptional.get();
                bookingUpdate.setGuest(booking.getGuest());
                bookingUpdate.setRoom(booking.getRoom());
                bookingUpdate.setCheckInDate(booking.getCheckInDate());
                bookingUpdate.setCheckOutDate(booking.getCheckOutDate());
            } else {
                bookingRepository.save(booking);
            }
        }
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }
}
