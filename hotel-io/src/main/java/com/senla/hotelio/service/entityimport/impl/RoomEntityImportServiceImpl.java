package com.senla.hotelio.service.entityimport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.entity.Room;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;

import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomEntityImportServiceImpl extends ImportService implements IImportService<Room> {
    private final String ENTITY_NAME = "Room";

    @Override
    public ArrayList<Room> importEntities() {
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<List<String>> roomsWithParameters = getEntitiesFromCsv(ENTITY_NAME);
        for (List<String> roomsWithParameter : roomsWithParameters) {
            rooms.add(new Room(
                    Long.parseLong(roomsWithParameter.get(0)),
                    Integer.parseInt(roomsWithParameter.get(1)),
                    Double.parseDouble(roomsWithParameter.get(2)),
                    Boolean.parseBoolean(roomsWithParameter.get(3)),
                    Long.parseLong(roomsWithParameter.get(4)),
                    Integer.parseInt(roomsWithParameter.get(5))
            ));
        }
        return rooms;
    }
}
