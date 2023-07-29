package com.senla.menu.action;

import com.senla.hotel.constant.Ordering;
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

    default Ordering getOrdering() {
        int ordering;
        String orderingText = "";

        boolean correct = false;
        while (!correct) {
            System.out.println("Select the type ordering: 0->ASC 1->DESC");
            ordering = scanner.nextInt();
            if (ordering != 1 && ordering != 0) {
                System.out.println("It is only input 0 or 1 are allowed. Try again.");
                continue;
            }
            if (ordering == 0) {
                orderingText = "ASC";
            } else {
                orderingText = "DESC";
            }
            correct = true;
        }
        return Ordering.valueOf(orderingText);
    }
}
