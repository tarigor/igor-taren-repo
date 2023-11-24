package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hoteldb.entity.RoomService;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "RoomServices";
    private final RoomServicesServiceImpl roomServicesService;

    @Autowired
    public RoomServiceEntityExportServiceImpl(RoomServicesServiceImpl roomServicesService) {
        this.roomServicesService = roomServicesService;
    }

    @Override
    public void exportEntity() {
        List<RoomService> roomServices = roomServicesService.getAll();
        storeEntityToCsv(ENTITY_FILENAME, roomServices);
    }
}
