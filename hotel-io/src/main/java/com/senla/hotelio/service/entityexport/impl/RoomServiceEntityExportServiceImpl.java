package com.senla.hotelio.service.entityexport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.entity.RoomService;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomServiceEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "RoomServices";

    @Override
    public void exportEntity() {
        List<RoomService> roomServices = RoomServicesServiceImpl.getInstance().getAll();
        storeEntityToCsv(ENTITY_FILENAME, roomServices);
    }
}
