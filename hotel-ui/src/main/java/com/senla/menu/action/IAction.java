package com.senla.menu.action;

import com.senla.hotelio.service.exception.HotelIoModuleException;
import com.serialization.exception.HotelSerializationModuleException;

public interface IAction {
    void execute() throws HotelIoModuleException, HotelSerializationModuleException;
}
