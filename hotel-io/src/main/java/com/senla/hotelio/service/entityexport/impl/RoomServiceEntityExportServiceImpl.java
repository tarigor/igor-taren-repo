package com.senla.hotelio.service.entityexport.impl;

import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import com.senla.betterthenspring.annotation.InjectValue;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hoteldb.entity.RoomService;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;

import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomServiceEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "RoomServices";
    private RoomServicesServiceImpl roomServicesService;

    @InjectValue
    public void setRoomServicesService(RoomServicesServiceImpl roomServicesService) {
        this.roomServicesService = roomServicesService;
    }

    @Override
    public void exportEntity() throws HotelIoModuleException {
        List<RoomService> roomServices = roomServicesService.getAll();
        storeEntityToCsv(ENTITY_FILENAME, roomServices);
    }
}
