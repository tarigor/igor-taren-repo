package com.senla.menu.action.impl;

import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuAction3 extends MenuAction implements IAction {
    private RoomServiceImpl roomService;

    @Autowired
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    //3=List of rooms sorted by number of stars
    @Override
    public void execute() {

//        roomService.findAllOrderedByStars().forEach(System.out::println);
    }
}
