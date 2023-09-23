package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotelio.service.entityexport.impl.*;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

import java.util.Scanner;

@CreateInstanceAndPutInContainer
public class MenuAction16 extends MenuAction implements IAction {
    protected BookingEntityExportServiceImpl bookingEntityExportService;
    private GuestEntityExportServiceImpl guestEntityExportService;
    private GuestServicesEntityExportServiceImpl guestServicesEntityExportService;
    private RoomEntityExportServiceImpl roomEntityExportService;
    private RoomServiceEntityExportServiceImpl roomServiceEntityExportService;

    @InjectValue(key = "BookingEntityExportServiceImpl")
    public void setBookingEntityExportService(BookingEntityExportServiceImpl bookingEntityExportService) {
        this.bookingEntityExportService = bookingEntityExportService;
    }

    @InjectValue(key = "GuestEntityExportServiceImpl")
    public void setGuestEntityExportService(GuestEntityExportServiceImpl guestEntityExportService) {
        this.guestEntityExportService = guestEntityExportService;
    }

    @InjectValue(key = "GuestServicesEntityExportServiceImpl")
    public void setGuestServicesEntityExportService(GuestServicesEntityExportServiceImpl guestServicesEntityExportService) {
        this.guestServicesEntityExportService = guestServicesEntityExportService;
    }

    @InjectValue(key = "RoomEntityExportServiceImpl")
    public void setRoomEntityExportService(RoomEntityExportServiceImpl roomEntityExportService) {
        this.roomEntityExportService = roomEntityExportService;
    }

    @InjectValue(key = "RoomServiceEntityExportServiceImpl")
    public void setRoomServiceEntityExportService(RoomServiceEntityExportServiceImpl roomServiceEntityExportService) {
        this.roomServiceEntityExportService = roomServiceEntityExportService;
    }

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
                    bookingEntityExportService.exportEntity();
                    break;
                case 1:
                    guestEntityExportService.exportEntity();
                    break;
                case 2:
                    guestServicesEntityExportService.exportEntity();
                    break;
                case 3:
                    roomEntityExportService.exportEntity();
                    break;
                case 4:
                    roomServiceEntityExportService.exportEntity();
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
