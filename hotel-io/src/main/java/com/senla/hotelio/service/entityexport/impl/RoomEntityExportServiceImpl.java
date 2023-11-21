package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hoteldb.entity.Room;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomEntityExportServiceImpl implements IExportService {
    private final String ENTITY_FILENAME = "Room";

    private final RoomServiceImpl roomService;
    private final ExportService exportService;
    @Autowired
    public RoomEntityExportServiceImpl(RoomServiceImpl roomService, ExportService exportService) {
        this.roomService = roomService;
        this.exportService = exportService;
    }

    @Override
    public void exportEntity() {
        List<Room> rooms = roomService.getAll();
        exportService.storeEntityToCsv(ENTITY_FILENAME, rooms);
    }
}
