package com.senla.menu.action.impl;

import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.menu.action.IAction;

public class MenuAction1 implements IAction {
    private final BookingServiceImpl service;

    public MenuAction1(BookingServiceImpl service) {
        this.service = service;
    }

    @Override
    public void execute() {
        System.out.println(service.findCountOfAllGuests());
        System.out.println("do action 1");
    }
}
