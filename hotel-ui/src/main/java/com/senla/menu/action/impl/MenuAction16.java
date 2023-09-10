package com.senla.menu.action.impl;

import com.senla.hotelio.service.entityexport.impl.*;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

import java.util.Scanner;

public class MenuAction16 extends MenuAction implements IAction {
    //Export the certain entity
    @Override
    public void execute() {
        System.out.println("im here1");
        int selection;
        boolean correct = false;
        while (!correct) {
            System.out.println("Please select an entity to be exported \n0->Booking\n1->Guest\n2->GuestServices\n3->Room\n4->RoomService");
            selection = new Scanner(System.in).nextInt();
            switch (selection) {
                case 0:
                    BookingEntityExportServiceImpl.getInstance().exportEntity();
                    break;
                case 1:
                    GuestEntityExportServiceImpl.getInstance().exportEntity();
                    break;
                case 2:
                    GuestServicesEntityExportServiceImpl.getInstance().exportEntity();
                    break;
                case 3:
                    RoomEntityExportServiceImpl.getInstance().exportEntity();
                    break;
                case 4:
                    RoomServiceEntityExportServiceImpl.getInstance().exportEntity();
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
