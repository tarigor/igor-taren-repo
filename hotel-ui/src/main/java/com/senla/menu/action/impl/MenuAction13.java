package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.RoomServiceSection;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

@CreateInstanceAndPutInContainer
public class MenuAction13 extends MenuAction implements IAction {
    private RoomServicesServiceImpl roomServicesService;

    @InjectValue
    public void setRoomServicesService(RoomServicesServiceImpl roomServicesService) {
        this.roomServicesService = roomServicesService;
    }

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
                    selectionText = "ROOM_SERVICE";
                    break;
                case 1:
                    selectionText = "PRICE";
                    break;
                default:
                    System.out.println("Wrong input! The selection must be in between 0-1. Try again");
                    continue;
            }
            correct = true;
        }

        roomServicesService.getAllOrdered(RoomServiceSection.valueOf(selectionText), getOrdering()).forEach(System.out::println);
    }
}
