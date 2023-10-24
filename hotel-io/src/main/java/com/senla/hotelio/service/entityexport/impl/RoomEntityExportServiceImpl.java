package com.senla.hotelio.service.entityexport.impl;

import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import com.senla.betterthenspring.annotation.InjectValue;
import com.senla.hotel.service.impl.RoomServiceImpl;
import com.senla.hoteldb.entity.Room;
import com.senla.hotelio.service.entityexport.ExportService;
import com.senla.hotelio.service.entityexport.IExportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;

import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomEntityExportServiceImpl extends ExportService implements IExportService {
    private final String ENTITY_FILENAME = "Room";

    private RoomServiceImpl roomService;

    @InjectValue
    public void setRoomService(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @Override
    public void exportEntity() throws HotelIoModuleException {
        List<Room> rooms = roomService.getAll();
        storeEntityToCsv(ENTITY_FILENAME, rooms);
    }
}
