package com.senla.menu.action.impl;

import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

public class MenuAction6 extends MenuAction implements IAction {
    //6=Total number of available rooms
    @Override
    public void execute() {
        System.out.println(roomService.findNumberOfAvailableRooms());
    }
}
