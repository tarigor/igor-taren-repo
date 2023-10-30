package com.senla.menu.action.impl;

import com.senla.hoteldb.entity.Booking;
import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.GuestServices;
import com.senla.hoteldb.entity.Room;
import com.senla.hoteldb.entity.RoomService;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;
import com.senla.serialization.exception.HotelSerializationModuleException;
import com.senla.serialization.service.DeserializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class MenuAction18 extends MenuAction implements IAction {
    private static final Logger logger = LoggerFactory.getLogger(MenuAction18.class);
    private Map<Long, Booking> bookings;
    private DeserializationService deserializationService;

    public void setBookings(Map<Long, Booking> bookings) {
        this.bookings = bookings;
    }

    @Autowired
    public void setDeserializationService(DeserializationService deserializationService) {
        this.deserializationService = deserializationService;
    }

    @Override
    public void execute() throws HotelSerializationModuleException {
        int selection;
        boolean correct = false;
        while (!correct) {
            System.out.println("Please select an entity to be de-serialized \n0->Booking\n1->Guest\n2->GuestServices\n3->Room\n4->RoomService");
            selection = new Scanner(System.in).nextInt();
            switch (selection) {
                case 0:
                    //Booking
                    HashMap<Long, Booking> bookings = (HashMap<Long, Booking>) deserializationService.deserializeToMap(Booking.class, "Booking");
                    toPrint(bookings);
                    break;
                case 1:
                    //Guest
                    HashMap<Long, Guest> guests = (HashMap<Long, Guest>) deserializationService.deserializeToMap(Guest.class, "Guest");
                    toPrint(guests);
                    break;
                case 2:
                    //GuestService
                    HashMap<Long, GuestServices> guestServices = (HashMap<Long, GuestServices>) deserializationService.deserializeToMap(GuestServices.class, "GuestServices");
                    toPrint(guestServices);
                    break;
                case 3:
                    //Room
                    HashMap<Long, Room> rooms = (HashMap<Long, Room>) deserializationService.deserializeToMap(Room.class, "Room");
                    toPrint(rooms);
                    break;
                case 4:
                    //RoomService
                    HashMap<Long, RoomService> roomServices = (HashMap<Long, RoomService>) deserializationService.deserializeToMap(RoomService.class, "RoomServices");
                    toPrint(roomServices);
                    break;
                default:
                    logger.error("Wrong input! The selection must be in between 0-4. Try again");
                    continue;
            }
            correct = true;
        }
    }

    private <T> void toPrint(Map<Long, T> map) {
        map.forEach((k, v) -> System.out.println("key->" + k + " value->" + v));
    }
}
