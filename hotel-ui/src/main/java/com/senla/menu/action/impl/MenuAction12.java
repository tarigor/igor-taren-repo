package com.senla.menu.action.impl;

import com.senla.menu.action.IAction;

public class MenuAction12 implements IAction {

    //12=Prices of services and rooms (sorted by CAPACITY,PRICE,AVAILABILITY,SERVICE,RATING) in ASC(DESC) manner
    @Override
    public void execute() {
        int selection = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Please select the ordering \n1->CAPACITY\n2->PRICE\n3->AVAILABILITY\n4->SERVICE\n5->RATING");
            selection = scanner.nextInt();
            if (selection != 1 && selection != 2 && selection != 3 && selection != 4 && selection != 5) {

            }
        }
    }
}
