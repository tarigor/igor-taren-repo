package com.senla.hotelio.service.entityimport.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.constant.ServiceType;
import com.senla.hotel.entity.RoomService;
import com.senla.hotelio.service.entityimport.IImportService;
import com.senla.hotelio.service.entityimport.ImportService;

import java.util.ArrayList;
import java.util.List;

@CreateInstanceAndPutInContainer
public class RoomServiceEntityImportServiceImpl extends ImportService implements IImportService<RoomService> {
    private final String ENTITY_NAME = "RoomService";

    @Override
    public ArrayList<RoomService> importEntities() {
        ArrayList<RoomService> roomServices = new ArrayList<>();
        ArrayList<List<String>> roomServicesWithParameters = getEntitiesFromCsv(ENTITY_NAME);
        for (List<String> roomServiceWithParameter : roomServicesWithParameters) {
            roomServices.add(new RoomService(
                    Long.parseLong(roomServiceWithParameter.get(0)),
                    ServiceType.valueOf(roomServiceWithParameter.get(1)).name(),
                    Double.parseDouble(roomServiceWithParameter.get(2))
            ));
        }
        return roomServices;
    }
}
