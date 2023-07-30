package com.senla.menu.action.impl;

import com.senla.menu.action.MenuAction;
import com.senla.menu.action.IAction;

public class MenuAction9 extends MenuAction implements IAction {
    //9=The amount of payment for the room to be paid by the guest
    @Override
    public void execute() {
        System.out.println("Input a guest ID");
        int guestId = scanner.nextInt();
        System.out.println("The total payment is:");
        System.out.println(bookingService.getTotalPaymentByGuest(guestId));
    }
}
