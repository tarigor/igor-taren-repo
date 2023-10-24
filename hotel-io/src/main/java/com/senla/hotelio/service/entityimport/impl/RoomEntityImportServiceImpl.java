package com.senla.hotelio.service.entityimport.impl;

import com.senla.betterthenspring.annotation.CreateInstanceAndPutInContainer;
import com.senla.hotel.constant.RoomStatus;
import com.senla.hoteldb.entity.Room;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;
import com.senla.hotelio.service.exception.HotelIoModuleException;

import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomEntityImportServiceImpl extends ImportService implements IImportService<Room> {
    private final String ENTITY_NAME = "Room";

    @Override
    public ArrayList<Room> importEntities() throws HotelIoModuleException {
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<List<String>> roomsWithParameters = getEntitiesFromCsv(ENTITY_NAME);
        for (List<String> roomsWithParameter : roomsWithParameters) {
            rooms.add(new Room(
                    Long.parseLong(roomsWithParameter.get(0)),
                    Integer.parseInt(roomsWithParameter.get(1)),
                    Double.parseDouble(roomsWithParameter.get(2)),
                    RoomStatus.valueOf(roomsWithParameter.get(3)).name(),
                    Integer.parseInt(roomsWithParameter.get(4))
            ));
        }
        return rooms;
    }
}
