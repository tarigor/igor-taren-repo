package com.senla.menu.action;

import com.senla.container.InstanceManager;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.service.impl.*;

import java.util.Scanner;


public abstract class MenuAction {
    protected Scanner scanner = new Scanner(System.in);
    protected BookingServiceImpl bookingService = (BookingServiceImpl) InstanceManager.getInstance("BookingServiceImpl");
    protected GuestServicesServiceImpl guestServicesService = (GuestServicesServiceImpl) InstanceManager.getInstance("GuestServicesServiceImpl");
    protected RoomServiceImpl roomService = (RoomServiceImpl) InstanceManager.getInstance("RoomServiceImpl");
    protected RoomServicesServiceImpl roomServicesService = (RoomServicesServiceImpl) InstanceManager.getInstance("RoomServicesServiceImpl");
    protected GuestServiceImpl guestService = (GuestServiceImpl) InstanceManager.getInstance("GuestServiceImpl");

    protected Ordering getOrdering() {
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
