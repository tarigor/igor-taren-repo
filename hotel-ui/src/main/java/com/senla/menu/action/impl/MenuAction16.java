package com.senla.menu.action.impl;

import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

public class MenuAction16 extends MenuAction implements IAction {
    //Export the certain entity
    @Override
    public void execute() {
        int selection;
        boolean correct = false;
        while (!correct) {
            System.out.println("Please select an entity to be exported \n0->Booking\n1->Guest\n2->GuestServices\n3->Room\n4->RoomService");
            selection = scanner.nextInt();
            switch (selection) {
                case 0:
                    exportService.storeEntityToCsv("Booking");
                    break;
                case 1:
                    exportService.storeEntityToCsv("Guest");
                    break;
                case 2:
                    exportService.storeEntityToCsv("GuestServices");
                    break;
                case 3:
                    exportService.storeEntityToCsv("Room");
                    break;
                case 4:
                    exportService.storeEntityToCsv("RoomService");
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
