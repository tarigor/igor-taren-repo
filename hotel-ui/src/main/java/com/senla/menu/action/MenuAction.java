package com.senla.menu.action;

import com.senla.hotel.constant.Ordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;


public abstract class MenuAction {
    private static final Logger logger = LoggerFactory.getLogger(MenuAction.class);
    protected Scanner scanner = new Scanner(System.in);

    protected Ordering getOrdering() {
        int ordering;
        String orderingText = "";

        boolean correct = false;
        while (!correct) {
            System.out.println("Select the type ordering: 0->ASC 1->DESC");
            ordering = scanner.nextInt();
            if (ordering != 1 && ordering != 0) {
                logger.error("It is only input 0 or 1 are allowed. Try again.");
                continue;
            }
            if (ordering == 0) {
                orderingText = "ASC";
            } else {
                orderingText = "DESC";
            }
            correct = true;
        }
        return Ordering.valueOf(orderingText);
    }
}
