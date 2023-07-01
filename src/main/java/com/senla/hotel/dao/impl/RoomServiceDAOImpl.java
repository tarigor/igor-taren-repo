package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IRoomServiceDAO;
import com.senla.hotel.entity.RoomService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomServiceDAOImpl implements IRoomServiceDAO {
    private static final RoomServiceDAOImpl INSTANCE = new RoomServiceDAOImpl();
    private Map<Long, RoomService> roomServices = new HashMap<>();

    public static RoomServiceDAOImpl getInstance() {
        return INSTANCE;
    }

    public void setRoomServices(List<RoomService> roomServices) {
        roomServices.forEach(this::save);
    }

    @Override
    public List<RoomService> getRoomServices() {
        return new ArrayList<>(roomServices.values());
    }

    @Override
    public RoomService getRoomServiceById(long serviceId) {
        return roomServices.get(serviceId);
    }

    @Override
    public void save(RoomService roomService) {
        this.roomServices.put(roomService.getId(), roomService);
    }
}
