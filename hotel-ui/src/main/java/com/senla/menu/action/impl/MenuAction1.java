package com.senla.menu.action.impl;

import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

public class MenuAction1 extends MenuAction implements IAction {
    //1=List of rooms sorted by price
    @Override
    public void execute() {
        roomService.findAllOrderedByPrice().forEach(System.out::println);
    }
}
