package com.senla.hotel.service.impl;

import com.senla.hotel.dto.BookingDto;
import com.senla.hotel.dto.GuestBookingDto;
import com.senla.hotel.dto.RoomDto;
import com.senla.hotel.service.IBookingService;
import com.senla.hotel.util.EntityDtoMapper;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final EntityDtoMapper entityDtoMapper;
    @Value("${number-of-guest-records-in-room-history}")
    private Integer roomHistoryLimit;

    public void setRoomHistoryLimit(Integer roomHistoryLimit) {
        this.roomHistoryLimit = roomHistoryLimit;
    }

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository, GuestRepository guestRepository, EntityDtoMapper entityDtoMapper) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.entityDtoMapper = entityDtoMapper;
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
    public List<BookingDto> findAllOrderedByCheckOutDate() {
        return bookingRepository.findAll().stream()
                .sorted(Comparator.comparing(Booking::getCheckOutDate))
                .limit(roomHistoryLimit)
                .map(booking -> entityDtoMapper.convertFromEntityToDto(booking, BookingDto.class))
                .collect(Collectors.toList());
    }

    //    View the last 3 guests of the room and the dates of their stay;
    @Override
    public List<BookingDto> findLastGuestOfRoomAndDates(int countOfGuests, long roomId) {
        return bookingRepository.findAll().stream()
                .sorted(Comparator.comparing(Booking::getCheckOutDate).reversed())
                .limit(countOfGuests)
                .map(booking -> entityDtoMapper.convertFromEntityToDto(booking, BookingDto.class))
                .collect(Collectors.toList());
    }

    //    The amount of payment for the room to be paid by the guest;
    @Override
    public double getTotalPaymentByGuest(long guestId) {
        List<Room> rooms = roomRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();
        double totalPayment = 0;
        for (Booking booking : bookings) {
            double payment = 0;
            if (booking.getGuest().getId() == guestId) {
                Duration duration = Duration.between(new Date(booking.getCheckInDate().getTime()).toInstant(), new Date(booking.getCheckOutDate().getTime()).toInstant());
                payment = duration.toDays() * rooms.stream().filter(r -> r.getId().equals(booking.getRoom().getId())).findAny().orElseThrow(() -> new NoSuchElementException("There is no such a booking with id->")).getPrice();
            }
            totalPayment = totalPayment + payment;
        }
        return totalPayment;
    }

    //    List of rooms that will be available on a certain date in the future;
    @Transactional
    @Override
    public List<RoomDto> findAvailableRoomsByDate(String dateString) {
        Date date;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Wrong date format. Proper format:dd-MM-yyyy");
        }
        List<RoomDto> availableRooms = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();
        List<RoomDto> roomDtoList = rooms.stream()
                .map(room -> entityDtoMapper.convertFromEntityToDto(room, RoomDto.class)).toList();
        for (RoomDto roomDto : roomDtoList) {
            if (isRoomAvailableOnDate(roomDto, date, bookings)) {
                availableRooms.add(roomDto);
            }
        }
        return availableRooms;
    }

    private boolean isRoomAvailableOnDate(RoomDto roomDto, Date dateToCheck, List<Booking> bookings) {
        for (Booking booking : bookings) {
            if (booking.getRoom().getId().equals(roomDto.getId())) {
                return dateToCheck.before(booking.getCheckInDate()) || dateToCheck.after(booking.getCheckOutDate());
            }
        }
        return true;
    }

    @Override
    public long findCountOfAllGuests(String dateString) {
        Date date;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Wrong date format. Proper format:dd-MM-yyyy");
        }
        return bookingRepository.findAll().stream()
                .filter(b -> (b.getCheckInDate().before(date) || b.getCheckInDate().equals(date)) && (b.getCheckOutDate().after(date) || b.getCheckOutDate().equals(date)))
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
                System.out.println("booking detected->" + bookingOptional);
                Booking bookingUpdate = bookingOptional.get();
                bookingUpdate.setGuest(booking.getGuest());
                bookingUpdate.setRoom(booking.getRoom());
                bookingUpdate.setCheckInDate(booking.getCheckInDate());
                bookingUpdate.setCheckOutDate(booking.getCheckOutDate());
                bookingRepository.save(bookingUpdate);
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
