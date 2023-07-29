package com.senla.menu.action.impl;

import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.menu.action.IAction;

public class MenuAction4 implements IAction {
    //4=List of guests and their rooms sorted alphabetically
    @Override
    public void execute() {
        bookingService.findAllOrderedAlphabetically().forEach(System.out::println);
    }
}
