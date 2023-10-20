package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotelio.service.entityexport.impl.BookingEntityExportServiceImpl;
import com.senla.hotelio.service.entityexport.impl.GuestEntityExportServiceImpl;
import com.senla.hotelio.service.entityexport.impl.GuestServicesEntityExportServiceImpl;
import com.senla.hotelio.service.entityexport.impl.RoomEntityExportServiceImpl;
import com.senla.hotelio.service.entityexport.impl.RoomServiceEntityExportServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

@CreateInstanceAndPutInContainer
public class MenuAction16 extends MenuAction implements IAction {
    private static final Logger logger = LoggerFactory.getLogger(MenuAction16.class);
    protected BookingEntityExportServiceImpl bookingEntityExportService;
    private GuestEntityExportServiceImpl guestEntityExportService;
    private GuestServicesEntityExportServiceImpl guestServicesEntityExportService;
    private RoomEntityExportServiceImpl roomEntityExportService;
    private RoomServiceEntityExportServiceImpl roomServiceEntityExportService;

    @InjectValue
    public void setBookingEntityExportService(BookingEntityExportServiceImpl bookingEntityExportService) {
        this.bookingEntityExportService = bookingEntityExportService;
    }

    @InjectValue
    public void setGuestEntityExportService(GuestEntityExportServiceImpl guestEntityExportService) {
        this.guestEntityExportService = guestEntityExportService;
    }

    @InjectValue
    public void setGuestServicesEntityExportService(GuestServicesEntityExportServiceImpl guestServicesEntityExportService) {
        this.guestServicesEntityExportService = guestServicesEntityExportService;
    }

    @InjectValue
    public void setRoomEntityExportService(RoomEntityExportServiceImpl roomEntityExportService) {
        this.roomEntityExportService = roomEntityExportService;
    }

    @InjectValue
    public void setRoomServiceEntityExportService(RoomServiceEntityExportServiceImpl roomServiceEntityExportService) {
        this.roomServiceEntityExportService = roomServiceEntityExportService;
    }

    //Export the certain entity
    @Override
    public void execute() {
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
                default:
                    logger.error("Wrong input! The selection must be in between 0-4. Try again");
                    continue;
            }
            correct = true;
        }
    }
}
