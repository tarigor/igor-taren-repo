package com.senla.menu.action.impl;

import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

public class MenuAction5 extends MenuAction implements IAction {
    //5=List of guests and their rooms sorted by check-out date
    @Override
    public void execute() {
        bookingService.findAllOrderedByCheckOutDate().forEach(System.out::println);
    }
}
