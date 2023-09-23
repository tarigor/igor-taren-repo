package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;
@CreateInstanceAndPutInContainer
public class MenuAction7 extends MenuAction implements IAction {
    //7=Total number of guests
    @Override
    public void execute() {
        System.out.println(bookingService.findCountOfAllGuests());
    }
}
