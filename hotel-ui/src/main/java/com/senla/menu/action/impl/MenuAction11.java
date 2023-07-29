package com.senla.menu.action.impl;

import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.menu.action.IAction;

public class MenuAction11 implements IAction {
    //11=View the list of guest services and their price (sorted by PRICE,DATE) in ASC(DESC) manner
    @Override
    public void execute() {
        System.out.println("Input the guest Id");
        int guestId = scanner.nextInt();
        boolean correct = false;
        int ordering = 0;
        while (!correct) {
            System.out.println("Select the type ordering: 0->ASC 1->DESC");
            ordering = scanner.nextInt();
            if (ordering != 1 && ordering != 0) {
                System.out.println("It is only input 0 or 1 are allowed. Try again.");
                continue;
            }
            correct = true;
        }
        if (ordering == 0) {
            System.out.println("sorted by date asc");
            guestServicesService.getByGuestIdSorted(guestId, GuestServicesSection.PRICE, Ordering.ASC).forEach(System.out::println);
        } else {
            System.out.println("sorted by date desc");
            guestServicesService.getByGuestIdSorted(guestId, GuestServicesSection.DATE, Ordering.DESC).forEach(System.out::println);
        }
    }
}
