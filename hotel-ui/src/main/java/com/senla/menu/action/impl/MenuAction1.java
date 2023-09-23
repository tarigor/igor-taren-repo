package com.senla.menu.action.impl;

import com.senla.container.InjectValue;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

public class MenuAction1 extends MenuAction implements IAction {
    private RoomServiceImpl roomService;

    @InjectValue(key = "RoomServiceImpl")
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    //1=List of rooms sorted by price
    @Override
    public void execute() {
        roomService.findAllOrderedByPrice().forEach(System.out::println);
    }
}
