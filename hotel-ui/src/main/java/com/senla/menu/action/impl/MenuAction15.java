package com.senla.menu.action.impl;

import com.senla.hotel.entity.Guest;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

import java.util.ArrayList;

public class MenuAction15 extends MenuAction implements IAction {
    //Import the certain entity from the CSV file
    @Override
    public void execute() {
        int selection;
        String selectionText = "";

        boolean correct = false;
        while (!correct) {
            System.out.println("Please select an entity to be imported \n0->Booking\n1->Guest\n2->GuestServices\n3->Room\n4->RoomService");
            selection = scanner.nextInt();
            switch (selection) {
                case 0:
                    System.out.println("before change");
                    bookingService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    bookingService.updateAllAndSaveIfNotExist(importService.getEntitiesFromCsv("Booking"));
                    System.out.println("after change");
                    bookingService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    break;
                case 1:
                    System.out.println("before change");
                    guestService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    ArrayList<Guest> guests = importService.getEntitiesFromCsv("Guest");
                    guestService.updateAllAndSaveIfNotExist(guests);
                    System.out.println("after change");
                    guestService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    break;
                case 2:
                    System.out.println("before change");
                    guestServicesService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    guestServicesService.updateAllAndSaveIfNotExist(importService.getEntitiesFromCsv("GuestServices"));
                    System.out.println("after change");
                    guestServicesService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    break;
                case 3:
                    roomService.updateAllAndSaveIfNotExist(importService.getEntitiesFromCsv("Room"));
                    roomService.getAll().forEach(System.out::println);
                    break;
                case 4:
                    roomServicesService.updateAllAndSaveIfNotExist(importService.getEntitiesFromCsv("RoomService"));
                    roomServicesService.getAll().forEach(System.out::println);
                    break;
                default: {
                    System.out.println("Wrong input! The selection must be in between 0-4. Try again");
                    continue;
                }
            }
            correct = true;
        }
    }
}
