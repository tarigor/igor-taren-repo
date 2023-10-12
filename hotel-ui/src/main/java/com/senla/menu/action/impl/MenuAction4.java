package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

@CreateInstanceAndPutInContainer
public class MenuAction4 extends MenuAction implements IAction {

    private BookingServiceImpl bookingService;

    @InjectValue
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    //4=List of guests and their rooms sorted alphabetically
    @Override
    public void execute() {
        bookingService.findAllOrderedAlphabetically().forEach(System.out::println);
    }
}
