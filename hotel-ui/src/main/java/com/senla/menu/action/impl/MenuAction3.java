package com.senla.menu.action.impl;

import com.senla.menu.action.MenuAction;
import com.senla.menu.action.IAction;

public class MenuAction3 extends MenuAction implements IAction {
    //3=List of rooms sorted by number of stars
    @Override
    public void execute() {
        roomService.findAllOrderedByStars().forEach(System.out::println);
    }
}
