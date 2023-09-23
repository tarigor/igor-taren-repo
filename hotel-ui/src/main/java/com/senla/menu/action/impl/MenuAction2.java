package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

public class MenuAction2 extends MenuAction implements IAction {
    //2=List of rooms sorted by capacity
    @Override
    public void execute() {
        roomService.findAllOrderedByCapacity().forEach(System.out::println);
    }
}
