package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.Table;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.dao.service.DAOService;
import com.senla.hotel.entity.RoomService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class RoomServiceDAOImpl implements IEntityDAO<RoomService> {
    private DAOService daoService;
    @InjectValue(key = "DAOService")
    public void setDaoService(DAOService daoService) {
        this.daoService = daoService;
    }
    private Map<Long, RoomService> roomServices = new HashMap<>();

    public Map<Long, RoomService> getRoomServices() {
        return roomServices;
    }

    public void setRoomServices(Map<Long, RoomService> roomServices) {
        this.roomServices = roomServices;
    }

    @Override
    public List<RoomService> getAll() {
        return daoService.getAll(Table.ROOM_SERVICE);
    }

    @Override
    public void saveAll(List<RoomService> roomServices) {
        daoService.saveAll(roomServices, Table.ROOM_SERVICE);
    }

    @Override
    public RoomService update(RoomService roomService) {
        return daoService.update(roomService, Table.ROOM_SERVICE);
    }

    @Override
    public RoomService getById(long serviceId) {
        return daoService.getById(serviceId, Table.ROOM_SERVICE);
    }

    @Override
    public void save(RoomService roomService) {
        daoService.save(roomService, Table.ROOM_SERVICE);
    }
}
