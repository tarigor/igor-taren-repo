package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.constant.RoomSection;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;
@CreateInstanceAndPutInContainer
public class MenuAction12 extends MenuAction implements IAction {

    //12=Prices of services and rooms (sorted by CAPACITY,PRICE,AVAILABILITY,SERVICE,RATING) in ASC(DESC) manner
    @Override
    public void execute() {
        int selection;
        String selectionText = "";

        boolean correct = false;
        while (!correct) {
            System.out.println("Please select the ordering \n0->ID\n1->CAPACITY\n2->PRICE\n3->AVAILABILITY\n4->SERVICE\n5->RATING");
            selection = scanner.nextInt();
            switch (selection) {
                case 0:
                    selectionText = "ID";
                    break;
                case 1:
                    selectionText = "CAPACITY";
                    break;
                case 2:
                    selectionText = "PRICE";
                    break;
                case 3:
                    selectionText = "AVAILABILITY";
                    break;
                case 4:
                    selectionText = "SERVICE";
                    break;
                case 5:
                    selectionText = "RATING";
                    break;
                default: {
                    System.out.println("Wrong input! The selection must be in between 0-5. Try again");
                    continue;
                }
            }
            correct = true;
        }

        roomService.getAllOrdered(RoomSection.valueOf(selectionText), getOrdering()).forEach(System.out::println);
    }
}
