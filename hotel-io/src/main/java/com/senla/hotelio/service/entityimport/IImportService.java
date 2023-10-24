package com.senla.hotelio.service.entityimport;

import com.senla.hotelio.service.exception.HotelIoModuleException;

import java.util.ArrayList;

public interface IImportService<T> {
    ArrayList<T> importEntities() throws HotelIoModuleException;
}
