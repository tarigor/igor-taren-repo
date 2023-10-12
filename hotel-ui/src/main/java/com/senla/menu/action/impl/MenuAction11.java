package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.service.impl.GuestServicesServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

@CreateInstanceAndPutInContainer
public class MenuAction11 extends MenuAction implements IAction {
    private GuestServicesServiceImpl guestServicesService;

    @InjectValue
    public void setGuestServicesService(GuestServicesServiceImpl guestServicesService) {
        this.guestServicesService = guestServicesService;
    }

    //11=View the list of guest services and their price (sorted by PRICE,DATE) in ASC(DESC) manner
    @Override
    public void execute() {
        int selection;
        String selectionText = "";

        System.out.println("Input the guest Id");
        int guestId = scanner.nextInt();

        boolean correct = false;
        while (!correct) {
            System.out.println("Please select the ordering \n0->PRICE\n1->DATE");
            selection = scanner.nextInt();
            switch (selection) {
                case 0:
                    selectionText = "PRICE";
                    break;
                case 1:
                    selectionText = "DATE";
                    break;
                default:
                    System.out.println("Wrong input! The selection must be in between 0-1. Try again");
                    continue;
            }
            correct = true;
        }

        guestServicesService.getByGuestIdSorted(guestId, GuestServicesSection.valueOf(selectionText), getOrdering()).forEach(System.out::println);
    }
}
