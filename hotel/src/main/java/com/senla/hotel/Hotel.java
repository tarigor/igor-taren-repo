package com.senla.hotel;

import com.senla.hotel.constant.ServiceType;
import com.senla.hotel.dao.impl.*;
import com.senla.hotel.entity.*;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class Hotel {
    private static final Hotel INSTANCE = new Hotel();
    private final List<Room> rooms;
    private final List<Guest> guests;
    private final List<Booking> bookings;
    private final List<RoomService> roomServices;
    private final List<GuestServices> guestServices;

    private final RoomServiceImpl roomService = RoomServiceImpl.getInstance();
    private final BookingServiceImpl bookingService = BookingServiceImpl.getInstance();
    private final GuestServicesServiceImpl guestServicesService = GuestServicesServiceImpl.getInstance();
    private final RoomServicesServiceImpl roomServicesService = RoomServicesServiceImpl.getInstance();

    {
        rooms = List.of(
                new Room(1, 23.2, false, 1, 3),
                new Room(2, 34.5, false, 1, 3),
                new Room(3, 47.2, true, 1, 3));

        guests = List.of(
                new Guest("Vasya", "Pupkin"),
                new Guest("Lelik", "Bolik"),
                new Guest("Olya", "Palkina"));

        bookings = List.of(
                new Booking(
                        2,
                        1,
                        1,
                        new GregorianCalendar(2023, Calendar.JUNE, 1).getTime(),
                        new GregorianCalendar(2023, Calendar.JUNE, 5).getTime()),
                new Booking(
                        1,
                        1,
                        2,
                        new GregorianCalendar(2023, Calendar.JUNE, 12).getTime(),
                        new GregorianCalendar(2023, Calendar.JUNE, 19).getTime()),
                new Booking(
                        3,
                        1,
                        3,
                        new GregorianCalendar(2023, Calendar.JUNE, 25).getTime(),
                        new GregorianCalendar(2023, Calendar.JUNE, 29).getTime()));

        roomServices = List.of(
                new RoomService(ServiceType.NONE, 12.3),
                new RoomService(ServiceType.MAINTENANCE, 22.7),
                new RoomService(ServiceType.REPAIR, 34.4),
                new RoomService(ServiceType.CLEANING, 10.4));

        guestServices = List.of(
                new GuestServices(1, Map.of(
                        new GregorianCalendar(2023, Calendar.JUNE, 1).getTime(), 1L,
                        new GregorianCalendar(2023, Calendar.JUNE, 2).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 3).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 4).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 5).getTime(), 4L
                )),
                new GuestServices(2, Map.of(
                        new GregorianCalendar(2023, Calendar.JUNE, 12).getTime(), 1L,
                        new GregorianCalendar(2023, Calendar.JUNE, 13).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 14).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 15).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 16).getTime(), 4L
                )),
                new GuestServices(3, Map.of(
                        new GregorianCalendar(2023, Calendar.JUNE, 25).getTime(), 1L,
                        new GregorianCalendar(2023, Calendar.JUNE, 26).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 27).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 28).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 29).getTime(), 4L
                )));

        RoomDAOImpl roomDAO = RoomDAOImpl.getInstance();
        roomDAO.saveAll(rooms);

        GuestDAOImpl guestDAO = GuestDAOImpl.getInstance();
        guestDAO.saveAll(guests);

        BookingDAOImpl bookingDAO = BookingDAOImpl.getInstance();
        bookingDAO.saveAll(bookings);

        RoomServiceDAOImpl roomServiceDAO = RoomServiceDAOImpl.getInstance();
        roomServiceDAO.saveAll(roomServices);

        GuestServicesDAOImpl guestServicesDAO = new GuestServicesDAOImpl();
        guestServicesDAO.saveAll(guestServices);

        roomService.setRoomDAO(roomDAO);

        bookingService.setBookingDAO(bookingDAO);
        bookingService.setGuestDAO(guestDAO);
        bookingService.setRoomDAO(roomDAO);

        guestServicesService.setGuestServicesDAO(guestServicesDAO);
        guestServicesService.setRoomServiceDAO(roomServiceDAO);

        roomServicesService.setRoomServiceDAO(roomServiceDAO);
    }

    public static void init(){

    }
}
