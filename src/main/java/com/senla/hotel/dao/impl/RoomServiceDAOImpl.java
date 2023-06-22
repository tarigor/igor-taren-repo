package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IRoomServiceDAO;
import com.senla.hotel.entity.RoomService;

import java.util.Map;

public class RoomServiceDAOImpl implements IRoomServiceDAO {
    private Map<Long,RoomService> roomServices;

    public void setRoomServices(Map<Long, RoomService> roomServices) {
        this.roomServices = roomServices;
    }
}
