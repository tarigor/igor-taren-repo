package com.senla.hotelio.service.entityexport.impl;

import com.senla.hotel.entity.Room;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;

public class RoomEntityExportServiceImpl extends ExportService implements IExportService {
    private static final RoomEntityExportServiceImpl INSTANCE = new RoomEntityExportServiceImpl();
    private final String ENTITY_FILENAME = "Room";
    private final List<Room> rooms = RoomServiceImpl.getInstance().getAll();

    public static RoomEntityExportServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void exportEntity() {
        storeEntityToCsv(ENTITY_FILENAME, rooms);
    }
}
