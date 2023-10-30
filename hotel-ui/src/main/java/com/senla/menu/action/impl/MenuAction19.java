package com.senla.menu.action.impl;

import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuAction19 extends MenuAction implements IAction {
    private static final Logger logger = LoggerFactory.getLogger(MenuAction19.class);
    private RoomServiceImpl roomService;

    @Autowired
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Override
    public void execute() {
        System.out.println("Input the room ID");
        int roomId = scanner.nextInt();
        int selection;
        boolean correct = false;
        while (!correct) {
            System.out.println("Please select an action \n0->check-in\n1->check-out");
            selection = new Scanner(System.in).nextInt();
            switch (selection) {
                case 0:
                    roomService.doCheckIn(roomId);
                    break;
                case 1:
                    //Guest
                    roomService.doCheckOut(roomId);
                    break;
                default:
                    logger.error("Wrong input! The selection must be in between 0-1. Try again");
                    continue;
            }
            correct = true;
        }
    }
}
