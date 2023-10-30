package com.senla.menu.action.impl;

import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuAction6 extends MenuAction implements IAction {
    private RoomServiceImpl roomService;

    @Autowired
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    //6=Total number of available rooms
    @Override
    public void execute() {
        System.out.println(roomService.findNumberOfAvailableRooms());
    }
}
