package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.entity.RoomService;
import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;

public class RoomServiceEntityExportServiceImpl extends ExportService implements IExportService {
    private static final RoomServiceEntityExportServiceImpl INSTANCE = new RoomServiceEntityExportServiceImpl();
    private final String ENTITY_FILENAME = "RoomServices";
    private final List<RoomService> roomServices = RoomServicesServiceImpl.getInstance().getAll();

    public static RoomServiceEntityExportServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void exportEntity() {
        storeEntityToCsv(ENTITY_FILENAME, roomServices);
    }
}
