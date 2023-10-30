package com.senla.menu.action.impl;

import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuAction14 extends MenuAction implements IAction {
    private RoomServiceImpl roomService;

    @Autowired
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    //14=Show the details of a separate room
    @Override
    public void execute() {
        System.out.println("Input the room Id");
        int roomId = scanner.nextInt();
        System.out.println(roomService.getRoom(roomId));
    }
}
