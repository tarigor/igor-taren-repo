package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.RoomServicesServiceImpl;
import com.senla.hoteldb.entity.RoomService;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceEntityExportServiceImpl implements IExportService {
    private final String ENTITY_FILENAME = "RoomServices";
    private final RoomServicesServiceImpl roomServicesService;
    private final ExportService exportService;

    @Autowired
    public RoomServiceEntityExportServiceImpl(RoomServicesServiceImpl roomServicesService, ExportService exportService) {
        this.roomServicesService = roomServicesService;
        this.exportService = exportService;
    }

    @Override
    public void exportEntity() {
        List<RoomService> roomServices = roomServicesService.getAll();
        exportService.storeEntityToCsv(ENTITY_FILENAME, roomServices);
    }
}
