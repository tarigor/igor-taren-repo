package com.senla.menu.action.impl;

import com.senla.menu.action.IAction;
import com.senla.menu.action.MenuAction;
import com.serialization.exception.HotelSerializationModuleException;
import com.serialization.service.SerializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuAction17 extends MenuAction implements IAction {
    private static final Logger logger = LoggerFactory.getLogger(MenuAction17.class);
    private SerializationService serializationService;

    @Autowired
    public void setSerializationService(SerializationService serializationService) {
        this.serializationService = serializationService;
    }

    @Override
    public void execute() throws HotelSerializationModuleException {
        int selection;
        boolean correct = false;
        while (!correct) {
            System.out.println("Please select an entity to be serialized \n0->Booking\n1->Guest\n2->GuestServices\n3->Room\n4->RoomService");
            selection = new Scanner(System.in).nextInt();
            switch (selection) {
                case 0:
                    //Booking
                    serializationService.selectToSerialize("Booking");
                    break;
                case 1:
                    //Guest
                    serializationService.selectToSerialize("Guest");
                    break;
                case 2:
                    //GuestService
                    serializationService.selectToSerialize("GuestServices");
                    break;
                case 3:
                    //Room
                    serializationService.selectToSerialize("Room");
                    break;
                case 4:
                    //RoomService
                    serializationService.selectToSerialize("RoomServices");
                    break;
                default:
                    logger.error("Wrong input! The selection must be in between 0-4. Try again");
                    continue;
            }
            correct = true;
        }
    }
}
