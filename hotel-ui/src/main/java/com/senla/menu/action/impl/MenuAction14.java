package com.senla.menu.action.impl;

import com.senla.menu.action.MenuAction;
import com.senla.menu.action.IAction;

public class MenuAction14 extends MenuAction implements IAction {
    //14=Show the details of a separate room
    @Override
    public void execute() {
        System.out.println("Input the room Id");
        int roomId = scanner.nextInt();
        System.out.println(roomService.getRoom(roomId));
    }
}
