package com.senla.hotelio.service.entityexport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.entity.Room;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;

import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "Room";

    private RoomServiceImpl roomService;

    @InjectValue(key = "RoomServiceImpl")
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Override
    public void exportEntity() {
        List<Room> rooms = roomService.getAll();
        storeEntityToCsv(ENTITY_FILENAME, rooms);
    }
}
