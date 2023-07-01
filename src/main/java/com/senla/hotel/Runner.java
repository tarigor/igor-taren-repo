package com.senla.hotel;

import com.senla.hotel.constant.ServiceStatus;
import com.senla.hotel.dao.impl.*;
import com.senla.hotel.entity.*;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class Runner {

    private final List<Room> rooms;
    private final List<Guest> guests;
    private final List<Booking> bookings;
    private final List<RoomService> roomServices;
    private final List<GuestServices> guestServices;

    private final RoomServiceImpl roomService = RoomServiceImpl.getInstance();
    private final BookingServiceImpl bookingService = BookingServiceImpl.getInstance();
    private final GuestServicesServiceImpl guestServicesService = GuestServicesServiceImpl.getInstance();

    {
        rooms = List.of(
                new Room(101L, 1, 23.2, false, 1, 3),
                new Room(102L, 2, 34.5, false, 1, 3),
                new Room(103L, 3, 47.2, false, 1, 3));

        guests = List.of(
                new Guest(1L, "Vasya", "Pupkin"),
                new Guest(2L, "Lelik", "Bolik"),
                new Guest(3L, "Olya", "Palkina"));

        bookings = List.of(
                new Booking(
                        1,
                        2,
                        1,
                        101,
                        new GregorianCalendar(2023, Calendar.JUNE, 1).getTime(),
                        new GregorianCalendar(2023, Calendar.JUNE, 5).getTime()),
                new Booking(
                        2,
                        1,
                        1,
                        102,
                        new GregorianCalendar(2023, Calendar.JUNE, 12).getTime(),
                        new GregorianCalendar(2023, Calendar.JUNE, 19).getTime()),
                new Booking(
                        3L,
                        3,
                        1,
                        103,
                        new GregorianCalendar(2023, Calendar.JUNE, 25).getTime(),
                        new GregorianCalendar(2023, Calendar.JUNE, 29).getTime()));

        roomServices = List.of(
                new RoomService(1, ServiceStatus.NONE, 12.3),
                new RoomService(2, ServiceStatus.MAINTENANCE, 22.7),
                new RoomService(3, ServiceStatus.REPAIR, 34.4),
                new RoomService(4, ServiceStatus.CLEANING, 10.4));

        guestServices = List.of(
                new GuestServices(1,1, Map.of(
                        new GregorianCalendar(2023, Calendar.JUNE, 1).getTime(), 1L,
                        new GregorianCalendar(2023, Calendar.JUNE, 2).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 3).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 4).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 5).getTime(), 4L
                )),
                new GuestServices(2,2, Map.of(
                        new GregorianCalendar(2023, Calendar.JUNE, 12).getTime(), 1L,
                        new GregorianCalendar(2023, Calendar.JUNE, 13).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 14).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 15).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 16).getTime(), 4L
                )),
                new GuestServices(3,3, Map.of(
                        new GregorianCalendar(2023, Calendar.JUNE, 25).getTime(), 1L,
                        new GregorianCalendar(2023, Calendar.JUNE, 26).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 27).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 28).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 29).getTime(), 4L
                )));

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

        guestServicesService.setGuestServicesDAO(guestServicesDAO);
        guestServicesService.setRoomServiceDAO(roomServiceDAO);
    }

    public static void main(String[] args) {

        Runner runner = new Runner();

        System.out.println("List of rooms (sort by price,  by capacity, by number of stars)");
        System.out.println("by price");
        runner.roomService.findAllOrderedByPrice().forEach(System.out::println);
        System.out.println("by number");
        runner.roomService.findAllOrderedByCapacity().forEach(System.out::println);
        System.out.println("by stars");
        runner.roomService.findAllOrderedByStars().forEach(System.out::println);

        System.out.println("\nList of guests and their rooms (sort alphabetically and by check-out date)");
        runner.bookingService.findAllOrderedAlphabetically().forEach(System.out::println);
        runner.bookingService.findAllOrderedByCheckOutDate().forEach(System.out::println);

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
        runner.guestServicesService.getGuestServicesSortedByPrice(1L).forEach(System.out::println);
        runner.guestServicesService.getGuestServicesSortedByDate(1L).forEach(System.out::println);

        System.out.println("\nPrices of services and rooms (sort by section(category), by price)");
        runner.roomService.getRoomsBySection().forEach(System.out::println);
        runner.roomService.getRoomsOrderedByPrice().forEach(System.out::println);
        runner.roomService.getRoomServicesOrderedByCategory().forEach(System.out::println);
        runner.roomService.getRoomServicesOrderedByPrice().forEach(System.out::println);

        System.out.println("\nShow the details of a separate room");
        System.out.println(runner.roomService.getRoom(102));
    }
}
