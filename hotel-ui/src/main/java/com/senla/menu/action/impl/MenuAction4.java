package com.senla.menu.action.impl;

import com.senla.menu.action.MenuAction;
import com.senla.menu.action.IAction;

public class MenuAction4 extends MenuAction implements IAction {
    //4=List of guests and their rooms sorted alphabetically
    @Override
    public void execute() {
        bookingService.findAllOrderedAlphabetically().forEach(System.out::println);
    }
}
