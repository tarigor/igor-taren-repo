package com.senla.menu.action;

import com.senla.hotel.exception.HotelModuleException;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import com.senla.serialization.exception.HotelSerializationModuleException;

public interface IAction {
    void execute() throws HotelIoModuleException, HotelSerializationModuleException, HotelModuleException;
}
