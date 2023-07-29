package com.senla.menu.action.impl;

import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.menu.action.IAction;

public class MenuAction3 implements IAction {
    //3=List of rooms sorted by number of stars
    @Override
    public void execute() {
        roomService.findAllOrderedByStars().forEach(System.out::println);
    }
}
