package com.senla.menu.action.impl;

import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuAction4 extends MenuAction implements IAction {

    private BookingServiceImpl bookingService;

    @Autowired
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    //4=List of guests and their rooms sorted alphabetically
    @Override
    public void execute() {
        bookingService.findAllOrderedAlphabetically().forEach(System.out::println);
    }
}
