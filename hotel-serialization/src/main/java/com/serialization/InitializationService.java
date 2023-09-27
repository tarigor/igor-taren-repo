package com.serialization;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.dao.impl.*;
import com.senla.hotel.entity.*;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;

import java.util.HashMap;

@CreateInstanceAndPutInContainer
public class InitializationService {
    private DeserializationService deserializationService;
    private BookingDAOImpl bookingDAO;
    private GuestDAOImpl guestDAO;
    private GuestServicesDAOImpl guestServicesDAO;
    private RoomDAOImpl roomDAO;
    private RoomServiceDAOImpl roomServiceDAO;
    private GuestServicesServiceImpl guestServicesService;

    @InjectValue(key = "DeserializationService")
    public void setDeserializationService(DeserializationService deserializationService) {
        this.deserializationService = deserializationService;
    }

    @InjectValue(key = "BookingDAOImpl")
    public void setBookingDAO(BookingDAOImpl bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @InjectValue(key = "GuestDAOImpl")
    public void setGuestDAO(GuestDAOImpl guestDAO) {
        this.guestDAO = guestDAO;
    }

    @InjectValue(key = "GuestServicesDAOImpl")
    public void setGuestServicesDAO(GuestServicesDAOImpl guestServicesDAO) {
        this.guestServicesDAO = guestServicesDAO;
    }

    @InjectValue(key = "RoomDAOImpl")
    public void setRoomDAO(RoomDAOImpl roomDAO) {
        this.roomDAO = roomDAO;
    }

    @InjectValue(key = "RoomServiceDAOImpl")
    public void setRoomServiceDAO(RoomServiceDAOImpl roomServiceDAO) {
        this.roomServiceDAO = roomServiceDAO;
    }

    @InjectValue(key = "GuestServicesServiceImpl")
    public void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        this.guestServicesService = guestServicesService;
    }

    public void init() {
        HashMap<Long, Booking> bookings = (HashMap<Long, Booking>) deserializationService.deserializeToMap(Booking.class, "Booking");
        HashMap<Long, Guest> guests = (HashMap<Long, Guest>) deserializationService.deserializeToMap(Guest.class, "Guest");
        HashMap<Long, GuestServices> guestServices = (HashMap<Long, GuestServices>) deserializationService.deserializeToMap(GuestServices.class, "GuestServices");
        HashMap<Long, Room> rooms = (HashMap<Long, Room>) deserializationService.deserializeToMap(Room.class, "Room");
        HashMap<Long, RoomService> roomServices = (HashMap<Long, RoomService>) deserializationService.deserializeToMap(RoomService.class, "RoomServices");

        bookingDAO.setBookings(bookings);
        guestDAO.setGuests(guests);
        guestServicesDAO.setGuestServices(guestServices);
        guestServicesService.saveAll(guestServices);
        roomDAO.setRooms(rooms);
        roomServiceDAO.setRoomServices(roomServices);
    }
}
