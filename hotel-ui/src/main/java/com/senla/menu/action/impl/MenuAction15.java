package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.hotel.service.impl.GuestServiceImpl;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hotelio.service.entityimport.impl.BookingEntityImportServiceImpl;
import com.senla.hotelio.service.entityimport.impl.GuestEntityImportServiceImpl;
import com.senla.hotelio.service.entityimport.impl.GuestServicesEntityImportServiceImpl;
import com.senla.hotelio.service.entityimport.impl.RoomEntityImportServiceImpl;
import com.senla.hotelio.service.entityimport.impl.RoomServiceEntityImportServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

@CreateInstanceAndPutInContainer
public class MenuAction15 extends MenuAction implements IAction {
    private BookingServiceImpl bookingService;
    private GuestServiceImpl guestService;
    private GuestServicesServiceImpl guestServicesService;
    private RoomServiceImpl roomService;
    private RoomServicesServiceImpl roomServicesService;
    private BookingEntityImportServiceImpl bookingEntityImportService;
    private GuestEntityImportServiceImpl guestEntityImportService;
    private GuestServicesEntityImportServiceImpl guestServicesEntityImportService;
    private RoomEntityImportServiceImpl roomEntityImportService;
    private RoomServiceEntityImportServiceImpl roomServiceEntityImportService;

    @InjectValue
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @InjectValue
    public void setGuestService(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @InjectValue
    public void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        this.guestServicesService = guestServicesService;
    }

    @InjectValue
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @InjectValue
    public void setRoomServicesService(RoomServicesServiceImpl roomServicesService) {
        this.roomServicesService = roomServicesService;
    }

    @InjectValue
    public void setBookingEntityImportService(BookingEntityImportServiceImpl bookingEntityImportService) {
        this.bookingEntityImportService = bookingEntityImportService;
    }

    @InjectValue
    public void setGuestEntityImportService(GuestEntityImportServiceImpl guestEntityImportService) {
        this.guestEntityImportService = guestEntityImportService;
    }

    @InjectValue
    public void setGuestServicesEntityImportService(GuestServicesEntityImportServiceImpl guestServicesEntityImportService) {
        this.guestServicesEntityImportService = guestServicesEntityImportService;
    }

    @InjectValue
    public void setRoomEntityImportService(RoomEntityImportServiceImpl roomEntityImportService) {
        this.roomEntityImportService = roomEntityImportService;
    }

    @InjectValue
    public void setRoomServiceEntityImportService(RoomServiceEntityImportServiceImpl roomServiceEntityImportService) {
        this.roomServiceEntityImportService = roomServiceEntityImportService;
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
                    bookingService.updateAllAndSaveIfNotExist(bookingEntityImportService.importEntities());
                    System.out.println("after change");
                    bookingService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    break;
                case 1:
                    System.out.println("before change");
                    guestService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    guestService.updateAllAndSaveIfNotExist(guestEntityImportService.importEntities());
                    System.out.println("after change");
                    guestService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    break;
                case 2:
                    System.out.println("before change");
                    guestServicesService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    guestServicesService.updateAllAndSaveIfNotExist(guestServicesEntityImportService.importEntities());
                    System.out.println("after change");
                    guestServicesService.getAll().forEach(System.out::println);
                    System.out.println("-------------");
                    break;
                case 3:
                    roomService.updateAllAndSaveIfNotExist(roomEntityImportService.importEntities());
                    roomService.getAll().forEach(System.out::println);
                    break;
                case 4:
                    roomServicesService.updateAllAndSaveIfNotExist(roomServiceEntityImportService.importEntities());
                    roomServicesService.getAll().forEach(System.out::println);
                    break;
                default:
                    System.out.println("Wrong input! The selection must be in between 0-4. Try again");
                    continue;
            }
            correct = true;
        }
    }
}
