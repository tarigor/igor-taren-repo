package com.senla.hotel.service.impl;

import com.senla.hotel.dto.BookingDto;
import com.senla.hotel.dto.GuestBookingDto;
import com.senla.hotel.dto.RoomDto;
import com.senla.hotel.util.EntityDtoMapper;
import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.Room;
import com.senla.hoteldb.repository.BookingRepository;
import com.senla.hoteldb.repository.GuestRepository;
import com.senla.hoteldb.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private GuestRepository guestRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private EntityDtoMapper entityDtoMapper;
    @InjectMocks
    private BookingServiceImpl bookingService;
    private List<Guest> guests;
    private List<Booking> bookings;
    private List<Room> rooms;

    @BeforeEach
    void setUp() throws ParseException {
        Guest guest1 = new Guest(1L, "Ivan", "Ivanov", "ivanov@mail.com", "pass", "role");
        Guest guest2 = new Guest(2L, "Petr", "Petrov", "petrov@mail.com", "pass", "role");

        Room room1 = new Room(1L, 1, 11.1, "OCCUPIED", 1);
        Room room2 = new Room(2L, 1, 34.2, "OCCUPIED", 1);

        Booking booking1 = new Booking(1L, guest1, room1, new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-11"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-12"));
        Booking booking2 = new Booking(2L, guest2, room2, new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-13"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-15"));
        Booking booking3 = new Booking(3L, guest1, room1, new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-16"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-19"));

        rooms = Arrays.asList(room1, room2);
        guests = Arrays.asList(guest1, guest2);
        bookings = Arrays.asList(booking1, booking2, booking3);
    }

    @Test
    void saveAllTest() {
        when(bookingRepository.saveAll(bookings)).thenReturn(bookings);

        List<Booking> savedBookings = bookingRepository.saveAll(bookings);

        assertEquals(bookings.size(), savedBookings.size());
    }

    @Test
    void findAllOrderedAlphabeticallyResultListSizeTest() {
        bookingService.setRoomHistoryLimit(3);

        when(guestRepository.findAll()).thenReturn(guests);
        when(bookingRepository.findAll()).thenReturn(bookings);

        List<GuestBookingDto> result = bookingService.findAllOrderedAlphabetically();

        assertEquals(3, result.size());
    }
    @Test
    void findAllOrderedAlphabeticallyGuestExistingTest() {
        bookingService.setRoomHistoryLimit(3);

        when(guestRepository.findAll()).thenReturn(guests);
        when(bookingRepository.findAll()).thenReturn(bookings);

        List<GuestBookingDto> result = bookingService.findAllOrderedAlphabetically();

        assertEquals("Petrov", result.get(2).getGuest().getLastName());
    }

    @Test
    void findAllOrderedByCheckOutDateResultSizeTest() {
        bookingService.setRoomHistoryLimit(3);

        when(bookingRepository.findAll()).thenReturn(bookings);
        when(entityDtoMapper.convertFromEntityToDto(any(Booking.class), eq(BookingDto.class)))
                .thenAnswer(invocation -> {
                    Booking booking = invocation.getArgument(0);
                    return new BookingDto(booking.getId(), booking.getGuest(), booking.getRoom(), booking.getCheckInDate(), booking.getCheckOutDate());
                });

        List<BookingDto> result = bookingService.findAllOrderedByCheckOutDate();

        assertEquals(3, result.size());
    }

    @Test
    void findAllOrderedByCheckOutDateDateSequenceTest() {
        bookingService.setRoomHistoryLimit(3);

        when(bookingRepository.findAll()).thenReturn(bookings);
        when(entityDtoMapper.convertFromEntityToDto(any(Booking.class), eq(BookingDto.class)))
                .thenAnswer(invocation -> {
                    Booking booking = invocation.getArgument(0);
                    return new BookingDto(booking.getId(), booking.getGuest(), booking.getRoom(), booking.getCheckInDate(), booking.getCheckOutDate());
                });

        List<BookingDto> result = bookingService.findAllOrderedByCheckOutDate();

        assertTrue(result.get(0).getCheckOutDate().before(result.get(1).getCheckOutDate()));
    }

    @Test
    void findLastGuestOfRoomAndDatesResultSizeTest() {
        int countOfGuests = 1;
        long roomId = 1L;

        when(bookingRepository.findAll()).thenReturn(bookings);
        when(entityDtoMapper.convertFromEntityToDto(any(Booking.class), eq(BookingDto.class)))
                .thenAnswer(invocation -> {
                    Booking booking = invocation.getArgument(0);
                    return new BookingDto(booking.getId(), booking.getGuest(), booking.getRoom(), booking.getCheckInDate(), booking.getCheckOutDate());
                });

        List<BookingDto> result = bookingService.findLastGuestOfRoomAndDates(countOfGuests, roomId);

        assertEquals(countOfGuests, result.size());
    }

    @Test
    void getTotalPaymentByGuestTest() {
        double expectedPayment = 44.4;

        when(roomRepository.findAll()).thenReturn(rooms);
        when(bookingRepository.findAll()).thenReturn(bookings);

        double result = bookingService.getTotalPaymentByGuest(1L);

        assertEquals(expectedPayment, result, 0.001);
    }

    @Test
    void findAvailableRoomsByDateTest() {
        String dateString = "14-11-2023";
        List<RoomDto> expectedAvailableRoomList = List.of(new RoomDto(rooms.get(0).getId(), rooms.get(0).getCapacity(), rooms.get(0).getPrice(), rooms.get(0).getRoomStatus(), rooms.get(0).getStarsRating()));

        when(bookingRepository.findAll()).thenReturn(bookings);
        when(roomRepository.findAll()).thenReturn(rooms);
        when(entityDtoMapper.convertFromEntityToDto(any(Room.class), eq(RoomDto.class)))
                .thenAnswer(invocation -> {
                    Room room = invocation.getArgument(0);
                    return new RoomDto(room.getId(), room.getCapacity(), room.getPrice(), room.getRoomStatus(), room.getStarsRating());
                });

        List<RoomDto> availableRooms = bookingService.findAvailableRoomsByDate(dateString);

        assertEquals(expectedAvailableRoomList, availableRooms);
    }

    @Test
    void findCountOfAllGuestsTest() {
        String date = "11-11-2023";
        long expectedAmountOfGuest = 1;

        when(bookingRepository.findAll()).thenReturn(bookings);

        long result = bookingService.findCountOfAllGuests(date);

        assertEquals(expectedAmountOfGuest, result);
    }

    @Test
    void getByGuestIdTest() {
        long guestId = 1L;
        Booking booking = bookings.get(0);

        when(bookingRepository.findAll()).thenReturn(bookings);

        Booking result = bookingService.getByGuestId(guestId);

        assertNotNull(result);
        assertEquals(booking, result);
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void updateAllAndSaveIfNotExistFindByIdMethodCallTest() throws ParseException {
        Booking existingBooking = new Booking(
                1L,
                new Guest(),
                new Room(),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-16"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-17"));
        Booking newBooking = new Booking(
                1L,
                new Guest(),
                new Room(),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-16"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-19"));

        when(bookingRepository.findById(existingBooking.getId())).thenReturn(Optional.of(existingBooking));
        when(bookingRepository.findById(newBooking.getId())).thenReturn(Optional.empty());
        when(bookingRepository.save(any(Booking.class))).thenReturn(null);

        bookingService.updateAllAndSaveIfNotExist(new ArrayList<>(List.of(existingBooking, newBooking)));

        verify(bookingRepository, times(2)).findById(anyLong());
        verify(bookingRepository, times(2)).save(any(Booking.class));
    }

    @Test
    void updateAllAndSaveIfNotExistSaveMethodCallTest() throws ParseException {
        Booking existingBooking = new Booking(
                1L,
                new Guest(),
                new Room(),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-16"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-17"));
        Booking newBooking = new Booking(
                1L,
                new Guest(),
                new Room(),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-16"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-19"));

        when(bookingRepository.findById(existingBooking.getId())).thenReturn(Optional.of(existingBooking));
        when(bookingRepository.findById(newBooking.getId())).thenReturn(Optional.empty());
        when(bookingRepository.save(any(Booking.class))).thenReturn(null);

        bookingService.updateAllAndSaveIfNotExist(new ArrayList<>(List.of(existingBooking, newBooking)));

        verify(bookingRepository, times(2)).save(any(Booking.class));
    }
    @Test
    void getAllResultSizeTest() {
        when(bookingRepository.findAll()).thenReturn(bookings);

        List<Booking> actualBookings = bookingService.getAll();

        assertEquals(bookings.size(), actualBookings.size());
    }

    @Test
    void getAllResultTest() {
        when(bookingRepository.findAll()).thenReturn(bookings);

        List<Booking> actualBookings = bookingService.getAll();

        assertEquals(bookings, actualBookings);
    }
}