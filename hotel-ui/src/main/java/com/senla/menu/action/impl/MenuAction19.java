package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

import java.util.Scanner;

@CreateInstanceAndPutInContainer
public class MenuAction19 extends MenuAction implements IAction {
    private RoomServiceImpl roomService;

    @InjectValue(key = "RoomServiceImpl")
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
                    System.out.println("Wrong input! The selection must be in between 0-1. Try again");
                    continue;
            }
            correct = true;
        }
    }
}
