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

    private RoomServiceImpl roomService = RoomServiceImpl.getInstance();
    private BookingServiceImpl bookingService = BookingServiceImpl.getInstance();
    private GuestServiceImpl guestService = GuestServiceImpl.getInstance();
    private GuestServicesServiceImpl guestServicesService = GuestServicesServiceImpl.getInstance();

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
                1,
                2,
                1,
                101,
                new GregorianCalendar(2023, Calendar.JUNE, 1).getTime(),
                new GregorianCalendar(2023, Calendar.JUNE, 5).getTime()));
        bookings.put(2L, new Booking(
                2,
                1,
                1,
                102,
                new GregorianCalendar(2023, Calendar.JUNE, 12).getTime(),
                new GregorianCalendar(2023, Calendar.JUNE, 19).getTime()));
        bookings.put(3L, new Booking(
                3L,
                3,
                1,
                103,
                new GregorianCalendar(2023, Calendar.JUNE, 25).getTime(),
                new GregorianCalendar(2023, Calendar.JUNE, 29).getTime()));

        RoomDAOImpl roomDAO = RoomDAOImpl.getInstance();
        roomDAO.setRooms(rooms);

        GuestDAOImpl guestDAO = GuestDAOImpl.getInstance();
        guestDAO.setGuests(guests);

        BookingDAOImpl bookingDAO = BookingDAOImpl.getInstance();
        bookingDAO.setBookings(bookings);

        RoomServiceDAOImpl roomServiceDAO = RoomServiceDAOImpl.getInstance();
        roomServiceDAO.setRoomServices(roomServices);

        GuestServicesDAOImpl guestServicesDAO = new GuestServicesDAOImpl();
        guestServicesDAO.setGuestServices(guestServices);

        roomService.setRoomDAO(roomDAO);
        roomService.setRoomServiceDAO(roomServiceDAO);

        bookingService.setBookingDAO(bookingDAO);
        bookingService.setGuestDAO(guestDAO);
        bookingService.setRoomDAO(roomDAO);

        guestService.setGuestDAO(guestDAO);

        guestServicesService.setGuestServicesDAO(guestServicesDAO);
        guestServicesService.setRoomServiceDAO(roomServiceDAO);
    }

    public static void main(String[] args) {

        Runner runner = new Runner();

        System.out.println("List of rooms (sort by price,  by capacity, by number of stars)");
        runner.roomService.findAllByPrice().forEach(System.out::println);
        runner.roomService.findAllByCapacity().forEach(System.out::println);
        runner.roomService.findAllByStars().forEach(System.out::println);

        System.out.println("\nList of guests and their rooms (sort alphabetically and by check-out date)");
        runner.bookingService.findAllSortByAlphabetically().forEach(System.out::println);
        runner.bookingService.findAllSortByCheckOutDate().forEach(System.out::println);

        System.out.println("\nTotal number of available rooms");
        System.out.println(runner.roomService.findNumberOfAvailableRooms());

        System.out.println("\nTotal number of guests");
        System.out.println(runner.bookingService.findCountOfAllGuests());

        System.out.println("\nList of rooms that will be available on a certain date in the future");
        runner.bookingService.findAvailableRoomsByDate(new GregorianCalendar(2023, 9, 15).getTime());

        System.out.println("\nThe amount of payment for the room to be paid by the guest");
        System.out.println(runner.bookingService.getTotalPaymentByGuest(1L));

        System.out.println("\nView the last 3 guests of the room and the dates of their stay");
        runner.bookingService.findLastGuestOfRoomAndDates(3, 101L).forEach(System.out::println);

        System.out.println("\nView the list of guest services and their price (sort by price, by date)");
        runner.guestServicesService.getGuestServicesByPrice(1L).forEach(System.out::println);
        runner.guestServicesService.getGuestServicesByDate(1L).forEach(System.out::println);

        System.out.println("\nPrices of services and rooms (sort by section(category), by price)");
        runner.roomService.getRoomsBySection().forEach(System.out::println);
        runner.roomService.getRoomsByPrice().forEach(System.out::println);
        runner.roomService.getRoomServicesByCategory().forEach(System.out::println);
        runner.roomService.getRoomServicesByPrice().forEach(System.out::println);

        System.out.println("\nShow the details of a separate room");
        System.out.println(runner.roomService.getRoom(102));
    }
}
