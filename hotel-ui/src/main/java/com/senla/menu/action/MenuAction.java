package com.senla.menu.action;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.service.impl.*;

import java.util.Scanner;

import com.senla.hotelio.service.impl.ExportService;
import com.senla.hotelio.service.impl.ImportService;
public abstract class MenuAction {
    protected Scanner scanner = new Scanner(System.in);
    protected BookingServiceImpl bookingService = BookingServiceImpl.getInstance();
    protected GuestServicesServiceImpl guestServicesService = GuestServicesServiceImpl.getInstance();
    protected RoomServiceImpl roomService = RoomServiceImpl.getInstance();
    protected RoomServicesServiceImpl roomServicesService = RoomServicesServiceImpl.getInstance();
    protected GuestServiceImpl guestService = GuestServiceImpl.getInstance();
    protected ImportService importService = ImportService.getInstance();
    protected ExportService exportService = ExportService.getInstance();
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
