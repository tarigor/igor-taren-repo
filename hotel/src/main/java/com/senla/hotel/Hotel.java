package com.senla.hotel;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.ServiceType;
import com.senla.hotel.dto.GuestServicesEntityDTO;
import com.senla.hotel.entity.Booking;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.RoomService;
import com.senla.hotel.service.impl.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;


@CreateInstanceAndPutInContainer
public class Hotel {
    private static BookingServiceImpl bookingService;
    private static GuestServiceImpl guestService;
    private static GuestServicesServiceImpl guestServicesService;
    private static RoomServicesServiceImpl roomServicesService;
    private static RoomServiceImpl roomService;

    @InjectValue(key = "BookingServiceImpl")
    public static void setBookingService(BookingServiceImpl bookingService) {
        Hotel.bookingService = bookingService;
    }

    @InjectValue(key = "GuestServiceImpl")
    public static void setGuestService(GuestServiceImpl guestService) {
        Hotel.guestService = guestService;
    }

    @InjectValue(key = "GuestServicesServiceImpl")
    public static void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        Hotel.guestServicesService = guestServicesService;
    }

    @InjectValue(key = "RoomServicesServiceImpl")
    public static void setRoomServicesService(RoomServicesServiceImpl roomServicesService) {
        Hotel.roomServicesService = roomServicesService;
    }

    @InjectValue(key = "RoomServiceImpl")
    public static void setRoomService(RoomServiceImpl roomService) {
        Hotel.roomService = roomService;
    }

    public static void init() {
        List<Room> rooms = List.of(
                new Room(1, 23.2, false, 1, 3),
                new Room(2, 34.5, false, 1, 3),
                new Room(3, 47.2, true, 1, 3));

        List<Guest> guests = List.of(
                new Guest("Vasya", "Pupkin"),
                new Guest("Lelik", "Bolik"),
                new Guest("Olya", "Kolya"));

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

        List<GuestServicesEntityDTO> guestServicesEntityDTOList = List.of(
                new GuestServicesEntityDTO(1, Map.of(
                        new GregorianCalendar(2023, Calendar.JUNE, 1).getTime(), 1L,
                        new GregorianCalendar(2023, Calendar.JUNE, 2).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 3).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 4).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 5).getTime(), 4L
                )),
                new GuestServicesEntityDTO(2, Map.of(
                        new GregorianCalendar(2023, Calendar.JUNE, 12).getTime(), 1L,
                        new GregorianCalendar(2023, Calendar.JUNE, 13).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 14).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 15).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 16).getTime(), 4L
                )),
                new GuestServicesEntityDTO(3, Map.of(
                        new GregorianCalendar(2023, Calendar.JUNE, 25).getTime(), 1L,
                        new GregorianCalendar(2023, Calendar.JUNE, 26).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 27).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 28).getTime(), 4L,
                        new GregorianCalendar(2023, Calendar.JUNE, 29).getTime(), 4L
                )));
        bookingService.saveAll(bookings);
        guestService.saveAll(guests);
        guestServicesService.saveAll(guestServicesEntityDTOList);
        roomService.saveAll(rooms);
        roomServicesService.saveAll(roomServices);

    }
}
