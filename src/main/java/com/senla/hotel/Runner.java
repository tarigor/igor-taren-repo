package com.senla.hotel;

import com.senla.hotel.constant.ServiceStatus;
import com.senla.hotel.dao.impl.*;
import com.senla.hotel.entity.*;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Runner {

    private Map<Long, Room> rooms;
    private Map<Long, Guest> guests;
    private Map<Long, Booking> bookings;
    private Map<Long, RoomService> roomServices;
    private Map<Long, GuestServices> guestServices;
    private RoomServiceImpl roomService;
    private BookingServiceImpl bookingService;
    private GuestServiceImpl guestService;

    private GuestServicesServiceImpl guestServicesServiceImpl;

    {
        rooms = new HashMap<>();
        guests = new HashMap<>();
        bookings = new HashMap<>();
        roomServices = new HashMap<>();
        guestServices = new HashMap<>();

        guests.put(1L, new Guest(1L, "Vasya", "Pupkin"));
        guests.put(2L, new Guest(2L, "Lelik", "Bolik"));
        guests.put(3L, new Guest(3L, "Olya", "Palkina"));

        roomServices.put(1L, new RoomService(1l, ServiceStatus.NONE, 12.3));
        roomServices.put(2L, new RoomService(2l, ServiceStatus.MAINTENANCE, 22.7));
        roomServices.put(3L, new RoomService(3l, ServiceStatus.REPAIR, 34.4));
        roomServices.put(4L, new RoomService(4l, ServiceStatus.CLEANING, 10.4));

        rooms.put(101L, new Room(101L, 1, 23.2, false, 1, 3));
        rooms.put(102L, new Room(102L, 2, 34.5, false, 1, 3));
        rooms.put(103L, new Room(103L, 3, 47.2, false, 1, 3));

        guestServices.put(1l, new GuestServices(1l, Map.of(
                new GregorianCalendar(2023, Calendar.JUNE, 1).getTime(), 1L,
                new GregorianCalendar(2023, Calendar.JUNE, 2).getTime(), 4L,
                new GregorianCalendar(2023, Calendar.JUNE, 3).getTime(), 4L,
                new GregorianCalendar(2023, Calendar.JUNE, 4).getTime(), 4L,
                new GregorianCalendar(2023, Calendar.JUNE, 5).getTime(), 4L
        )));
        guestServices.put(1l, new GuestServices(2l, Map.of(
                new GregorianCalendar(2023, Calendar.JUNE, 12).getTime(), 1L,
                new GregorianCalendar(2023, Calendar.JUNE, 13).getTime(), 4L,
                new GregorianCalendar(2023, Calendar.JUNE, 14).getTime(), 4L,
                new GregorianCalendar(2023, Calendar.JUNE, 15).getTime(), 4L,
                new GregorianCalendar(2023, Calendar.JUNE, 16).getTime(), 4L
        )));
        guestServices.put(1l, new GuestServices(3l, Map.of(
                new GregorianCalendar(2023, Calendar.JUNE, 25).getTime(), 1L,
                new GregorianCalendar(2023, Calendar.JUNE, 26).getTime(), 4L,
                new GregorianCalendar(2023, Calendar.JUNE, 27).getTime(), 4L,
                new GregorianCalendar(2023, Calendar.JUNE, 28).getTime(), 4L,
                new GregorianCalendar(2023, Calendar.JUNE, 29).getTime(), 4L
        )));

        bookings.put(1L, new Booking(
                1L,
                2l,
                101,
                1l,
                new GregorianCalendar(2023, Calendar.JUNE, 1).getTime(),
                new GregorianCalendar(2023, Calendar.JUNE, 5).getTime()));
        bookings.put(2L, new Booking(
                2L,
                1l,
                102,
                2l,
                new GregorianCalendar(2023, Calendar.JUNE, 12).getTime(),
                new GregorianCalendar(2023, Calendar.JUNE, 19).getTime()));
        bookings.put(3L, new Booking(
                3L,
                3l,
                103,
                3l,
                new GregorianCalendar(2023, Calendar.JUNE, 25).getTime(),
                new GregorianCalendar(2023, Calendar.JUNE, 29).getTime()));

        RoomDAOImpl roomDAO = new RoomDAOImpl();
        roomDAO.setRooms(rooms);

        GuestDAOImpl guestDAO = new GuestDAOImpl();
        guestDAO.setGuests(guests);

        BookingDAOImpl bookingDAO = new BookingDAOImpl();
        bookingDAO.setBookings(bookings);

        RoomServiceDAOImpl roomServiceDAO = new RoomServiceDAOImpl();
        roomServiceDAO.setRoomServices(roomServices);

        GuestServicesDAOImpl guestServicesDAO = new GuestServicesDAOImpl();
        guestServicesDAO.setGuestServices(guestServices);

        roomService = new RoomServiceImpl();
        roomService.setRoomDAO(roomDAO);
        roomService.setRoomServiceDAO(roomServiceDAO);

        bookingService = new BookingServiceImpl();
        bookingService.setBookingDAO(bookingDAO);

        guestService = new GuestServiceImpl();
        guestService.setGuestDAO(guestDAO);

        bookingService.setRoomDAO(roomDAO);

        guestServicesServiceImpl = new GuestServicesServiceImpl();
        guestServicesServiceImpl.setGuestServicesDAO(guestServicesDAO);
    }

    public static void main(String[] args) {
        Runner runner = new Runner();

//        runner.bookingService.findAllSortByAlphabetically().forEach(System.out::println);
//
//        runner.bookingService.findAllSortByCheckOutDate().forEach(Booking::printAllFields);

        //runner.bookingService.findLastGuestOfRoomAndDates(2,1).forEach(Booking::printAllFields);
        //System.out.println(runner.bookingService.getTotalPaymentByGuest(1));

        //runner.bookingService.findAvailableRoomsByDate(new GregorianCalendar(2023, 9, 15).getTime()).forEach(System.out::println);

        //System.out.println(runner.bookingService.findCountOfAllGuests());

        System.out.println(runner.guestServicesServiceImpl.getGuestServicesByPrice(1l).getServicesOrdered());
    }
}
