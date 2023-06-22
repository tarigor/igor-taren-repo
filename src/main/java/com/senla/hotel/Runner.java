package com.senla.hotel;

import com.senla.hotel.constant.ServiceStatus;
import com.senla.hotel.dao.impl.BookingDAOImpl;
import com.senla.hotel.dao.impl.GuestDAOImpl;
import com.senla.hotel.dao.impl.RoomDAOImpl;
import com.senla.hotel.dao.impl.RoomServiceDAOImpl;
import com.senla.hotel.entity.Booking;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.RoomService;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Runner {

    private Map<Long, Room> rooms;
    private Map<Long, Guest> guests;
    private Map<Long, Booking> bookings;
    private Map<Long, RoomService> roomServices;
    private RoomServiceImpl roomService;
    private BookingServiceImpl bookingService;
    private GuestServiceImpl guestService;

    {
        rooms = new HashMap<>();
        guests = new HashMap<>();
        bookings = new HashMap<>();
        roomServices = new HashMap<>();

        guests.put(1L, new Guest(1L, "Vasya", "Pupkin"));
        guests.put(2L, new Guest(2L, "Lelik", "Bolik"));
        guests.put(3L, new Guest(3L, "Olya", "Palkina"));

        roomServices.put(1L, new RoomService(ServiceStatus.NONE, 12.3));
        roomServices.put(1L, new RoomService(ServiceStatus.MAINTENANCE, 22.7));
        roomServices.put(1L, new RoomService(ServiceStatus.REPAIR, 34.4));

        rooms.put(1L, new Room(1L, roomServices.get(1L), 2, 23.3, true, 3));
        rooms.put(2L, new Room(2L, roomServices.get(1L), 3, 42.1, false, 3));
        rooms.put(3L, new Room(3L, roomServices.get(1L), 2, 25.7, true, 3));

        bookings.put(1L, new Booking(
                1L,
                guests.get(2L),
                101,
                new Date(2023, 9, 22),
                new Date(2023, 9, 25)));
        bookings.put(2L, new Booking(
                2L,
                guests.get(1L),
                102,
                new Date(2023, 11, 12),
                new Date(2023, 11, 20)));
        bookings.put(3L, new Booking(
                3L,
                guests.get(3L),
                103,
                new Date(2023, 8, 25),
                new Date(2023, 9, 1)));

        RoomDAOImpl roomDAO = new RoomDAOImpl();
        roomDAO.setRooms(rooms);

        GuestDAOImpl guestDAO = new GuestDAOImpl();
        guestDAO.setGuests(guests);

        BookingDAOImpl bookingDAO = new BookingDAOImpl();
        bookingDAO.setBookings(bookings);

        RoomServiceDAOImpl roomServiceDAO = new RoomServiceDAOImpl();
        roomServiceDAO.setRoomServices(roomServices);

        roomService = new RoomServiceImpl();
        roomService.setRoomDAO(roomDAO);
        roomService.setRoomServiceDAO(roomServiceDAO);

        bookingService = new BookingServiceImpl();
        bookingService.setBookingDAO(bookingDAO);

        guestService = new GuestServiceImpl();
        guestService.setGuestDAO(guestDAO);

    }

    public static void main(String[] args) {
        Runner runner = new Runner();

        runner.bookingService.findAllSortByAlphabetically().forEach(System.out::println);
    }
}
