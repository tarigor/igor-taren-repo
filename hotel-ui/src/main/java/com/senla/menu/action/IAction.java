package com.senla.menu.action;

import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;

import java.util.Scanner;

public interface IAction {
    Scanner scanner = new Scanner(System.in);
    BookingServiceImpl bookingService = BookingServiceImpl.getInstance();
    GuestServicesServiceImpl guestServicesService = GuestServicesServiceImpl.getInstance();
    RoomServiceImpl roomService = RoomServiceImpl.getInstance();
    RoomServicesServiceImpl roomServicesService = RoomServicesServiceImpl.getInstance();

    void execute();
}
