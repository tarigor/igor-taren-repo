package com.senla.hotel;

import com.senla.hotel.constant.ServiceType;
import com.senla.hotel.entity.*;
import com.senla.hotel.service.impl.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class Hotel {
    private static final Hotel INSTANCE = new Hotel();

    {
        BookingServiceImpl bookingService = BookingServiceImpl.getInstance();
        GuestServiceImpl guestService = GuestServiceImpl.getInstance();
        GuestServicesServiceImpl guestServicesService = GuestServicesServiceImpl.getInstance();
        RoomServiceImpl roomService = RoomServiceImpl.getInstance();
        RoomServicesServiceImpl roomServicesService = RoomServicesServiceImpl.getInstance();

        List<Room> rooms = List.of(
                new Room(1, 23.2, false, 1, 3),
                new Room(2, 34.5, false, 1, 3),
                new Room(3, 47.2, true, 1, 3));

        List<Guest> guests = List.of(
                new Guest("Vasya", "Pupkin"),
                new Guest("Lelik", "Bolik"),
                new Guest("Olya", "Palkina"));

        List<Booking> bookings = List.of(
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

        List<RoomService> roomServices = List.of(
                new RoomService(ServiceType.NONE, 12.3),
                new RoomService(ServiceType.MAINTENANCE, 22.7),
                new RoomService(ServiceType.REPAIR, 34.4),
                new RoomService(ServiceType.CLEANING, 10.4));

        List<GuestServices> guestServices = List.of(
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
        bookingService.saveAll(bookings);
        guestService.saveAll(guests);
        guestServicesService.saveAll(guestServices);
        roomService.saveAll(rooms);
        roomServicesService.saveAll(roomServices);
    }

    public static void init() {

    }
}
