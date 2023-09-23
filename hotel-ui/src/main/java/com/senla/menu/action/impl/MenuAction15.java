package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.service.impl.*;
import com.senla.hotelio.service.entityimport.impl.*;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

@CreateInstanceAndPutInContainer
public class MenuAction15 extends MenuAction implements IAction {
    private BookingServiceImpl bookingService;

    @InjectValue(key = "BookingServiceImpl")
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    private GuestServiceImpl guestService;

    @InjectValue(key = "GuestServiceImpl")
    public void setGuestService(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    private GuestServicesServiceImpl guestServicesService;

    @InjectValue(key = "GuestServicesServiceImpl")
    public void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        this.guestServicesService = guestServicesService;
    }

    private RoomServiceImpl roomService;

    @InjectValue(key = "RoomServiceImpl")
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    private RoomServicesServiceImpl roomServicesService;

    @InjectValue(key = "RoomServicesServiceImpl")
    public void setRoomServicesService(RoomServicesServiceImpl roomServicesService) {
        this.roomServicesService = roomServicesService;
    }

    //Import the certain entity from the CSV file
    @Override
    public void execute() {
        int selection;
        boolean correct = false;
        while (!correct) {
            System.out.println("Please select an entity to be imported \n0->Booking\n1->Guest\n2->GuestServices\n3->Room\n4->RoomService");
            selection = scanner.nextInt();
            switch (selection) {
                case 0:
                    System.out.println("before change");
                    bookingService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    bookingService.updateAllAndSaveIfNotExist(BookingEntityImportServiceImpl.getInstance().importEntities());
                    System.out.println("after change");
                    bookingService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    break;
                case 1:
                    System.out.println("before change");
                    guestService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    guestService.updateAllAndSaveIfNotExist(GuestEntityImportServiceImpl.getInstance().importEntities());
                    System.out.println("after change");
                    guestService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    break;
                case 2:
                    System.out.println("before change");
                    guestServicesService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    guestServicesService.updateAllAndSaveIfNotExist(GuestServicesEntityImportServiceImpl.getInstance().importEntities());
                    System.out.println("after change");
                    guestServicesService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    break;
                case 3:
                    roomService.updateAllAndSaveIfNotExist(RoomEntityImportServiceImpl.getInstance().importEntities());
                    roomService.getAll().forEach(System.out::println);
                    break;
                case 4:
                    roomServicesService.updateAllAndSaveIfNotExist(RoomServiceEntityImportServiceImpl.getInstance().importEntities());
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
