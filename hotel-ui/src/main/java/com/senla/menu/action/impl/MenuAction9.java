package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

@CreateInstanceAndPutInContainer
public class MenuAction9 extends MenuAction implements IAction {
    private BookingServiceImpl bookingService;

    @InjectValue(key = "BookingServiceImpl")
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    //9=The amount of payment for the room to be paid by the guest
    @Override
    public void execute() {
        System.out.println("Input a guest ID");
        int guestId = scanner.nextInt();
        System.out.println("The total payment is:");
        System.out.println(bookingService.getTotalPaymentByGuest(guestId));
    }
}
