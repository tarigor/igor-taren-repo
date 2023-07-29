package com.senla.menu.action.impl;

import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.menu.action.IAction;

public class MenuAction6 implements IAction {
    //6=Total number of available rooms
    @Override
    public void execute() {
        System.out.println(roomService.findNumberOfAvailableRooms());
    }
}
