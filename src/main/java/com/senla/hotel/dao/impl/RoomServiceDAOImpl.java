package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IRoomServiceDAO;
import com.senla.hotel.entity.RoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomServiceDAOImpl implements IRoomServiceDAO {
    private Map<Long, RoomService> roomServices;

    public void setRoomServices(Map<Long, RoomService> roomServices) {
        this.roomServices = roomServices;
    }

    @Override
    public List<RoomService> getRoomServices() {
        return new ArrayList<>(roomServices.values());
    }

    @Override
    public RoomService getRoomServiceById(long serviceId) {
        return roomServices.get(serviceId);
    }
}
