package com.senla.hotelio.service.entityimport.impl;

import com.senla.hotel.entity.Room;
import com.senla.hotelio.service.entityimport.ImportService;
import com.senla.hotelio.service.entityimport.IImportService;

import java.util.ArrayList;
import java.util.List;

public class RoomEntityImportServiceImpl extends ImportService implements IImportService<Room> {
    private static final RoomEntityImportServiceImpl INSTANCE = new RoomEntityImportServiceImpl();
    private final String ENTITY_NAME = "Room";

    public static RoomEntityImportServiceImpl getInstance() {
        return INSTANCE;
    }

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
