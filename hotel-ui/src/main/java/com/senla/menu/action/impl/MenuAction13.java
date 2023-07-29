package com.senla.menu.action.impl;

import com.senla.hotel.constant.RoomServiceSection;
import com.senla.menu.action.IAction;

public class MenuAction13 implements IAction {
    //13=Room services (ordered by ROME_SERVICES,PRICE) in ASC(DESC) manner
    @Override
    public void execute() {
        int selection;
        String selectionText = "";

        boolean correct = false;
        while (!correct) {
            System.out.println("Please select the ordering \n0->ROOM SERVICES\n1->PRICE");
            selection = scanner.nextInt();
            switch (selection) {
                case 0:
                    selectionText = "ROME_SERVICES";
                    break;
                case 1:
                    selectionText = "PRICE";
                    break;
                default: {
                    System.out.println("Wrong input! The selection must be in between 0-1. Try again");
                    continue;
                }
            }
            correct = true;
        }

        roomServicesService.getAllOrdered(RoomServiceSection.valueOf(selectionText), getOrdering()).forEach(System.out::println);
    }
}
