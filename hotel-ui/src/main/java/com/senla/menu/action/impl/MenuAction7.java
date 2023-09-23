package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

@CreateInstanceAndPutInContainer
public class MenuAction7 extends MenuAction implements IAction {
    private BookingServiceImpl bookingService;

    @InjectValue(key = "BookingServiceImpl")
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    //7=Total number of guests
    @Override
    public void execute() {
        System.out.println(bookingService.findCountOfAllGuests());
    }
}
