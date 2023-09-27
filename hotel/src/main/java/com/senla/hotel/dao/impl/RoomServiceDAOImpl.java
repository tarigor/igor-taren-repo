package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.RoomService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class RoomServiceDAOImpl implements IEntityDAO<RoomService> {
    private Map<Long, RoomService> roomServices = new HashMap<>();
    @Override
    public List<RoomService> getAll() {
        return new ArrayList<>(roomServices.values());
    }

    @Override
    public void saveAll(List<RoomService> roomServices) {
        roomServices.forEach(this::save);
    }

    @Override
    public RoomService update(RoomService roomService) {
        RoomService updatedRoomService = getById(roomService.getId());
        updatedRoomService.setServiceType(roomService.getServiceType());
        updatedRoomService.setPrice(roomService.getPrice());
        return null;
    }

    @Override
    public RoomService getById(long serviceId) {
        return roomServices.get(serviceId);
    }

    @Override
    public void save(RoomService roomService) {
        this.roomServices.put(roomService.getId(), roomService);
    }
}
