package com.senla.menu.action.impl;

import com.senla.menu.action.IAction;

public class MenuAction5 implements IAction {
    //5=List of guests and their rooms sorted by check-out date
    @Override
    public void execute() {
        bookingService.findAllOrderedByCheckOutDate().forEach(System.out::println);
    }
}
