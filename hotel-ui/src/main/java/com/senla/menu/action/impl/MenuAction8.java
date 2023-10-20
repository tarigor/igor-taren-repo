package com.senla.menu.action.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.service.impl.BookingServiceImpl;
import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.GregorianCalendar;

@CreateInstanceAndPutInContainer
public class MenuAction8 extends MenuAction implements IAction {
    private static final Logger logger = LoggerFactory.getLogger(MenuAction8.class);
    private BookingServiceImpl bookingService;

    @InjectValue
    public void setBookingService(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    //8=List of rooms that will be available on a certain date in the future
    @Override
    public void execute() {
        boolean correct = false;
        int year = 0;
        int month = 0;
        int day = 0;
        while (!correct) {
            System.out.println("Enter an year in format YYYY");
            year = scanner.nextInt();
            System.out.println("Enter an month number");
            month = scanner.nextInt();
            if (month > 12) {
                logger.error("The month is limited by number 12");
                continue;
            }
            System.out.println("Enter an day number");
            day = scanner.nextInt();
            if (day > 31) {
                logger.error("The month is limited by number 31");
                continue;
            }
            correct = true;
        }
        System.out.println("The available rooms on this date " + day + "." + month + "." + year + ":");
        bookingService.findAvailableRoomsByDate(
                        new GregorianCalendar(year, month + 1, day)
                                .getTime())
                .forEach(System.out::println);
    }
}
