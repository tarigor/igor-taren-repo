package com.senla.menu.action.impl;

import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

public class MenuAction7 extends MenuAction implements IAction {
    //7=Total number of guests
    @Override
    public void execute() {
        System.out.println(bookingService.findCountOfAllGuests());
    }
}
