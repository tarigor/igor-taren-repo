package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

@CreateInstanceAndPutInContainer
public class MenuAction5 extends MenuAction implements IAction {
    private BookingServiceImpl bookingService;

    @InjectValue(key = "BookingServiceImpl")
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    //5=List of guests and their rooms sorted by check-out date
    @Override
    public void execute() {
        bookingService.findAllOrderedByCheckOutDate().forEach(System.out::println);
    }
}
