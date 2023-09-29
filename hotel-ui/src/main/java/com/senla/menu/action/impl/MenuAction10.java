package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;

@CreateInstanceAndPutInContainer
public class MenuAction10 extends MenuAction implements IAction {
    private BookingServiceImpl bookingService;

    @InjectValue(key = "BookingServiceImpl")
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    //10=View the last 3 guests of the room and the dates of their stay
    @Override
    public void execute() {
        System.out.println("Input the amount of guests");
        int guestAmount = scanner.nextInt();
        System.out.println("Input the room ID");
        int roomId = scanner.nextInt();
        bookingService.findLastGuestOfRoomAndDates(guestAmount, roomId).forEach(System.out::println);
    }
}
