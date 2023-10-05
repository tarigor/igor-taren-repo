package com.senla.hotel.dao.impl;

import com.senla.container.CreateInstanceAndPutInContainer;
import com.senla.container.InjectValue;
import com.senla.hotel.constant.Table;
import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.dao.service.DAOService;
import com.senla.hotel.entity.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CreateInstanceAndPutInContainer
public class RoomDAOImpl implements IEntityDAO<Room> {
    private DAOService daoService;
    @InjectValue(key = "DAOService")
    public void setDaoService(DAOService daoService) {
        this.daoService = daoService;
    }
    private Map<Long, Room> rooms = new HashMap<>();

    public Map<Long, Room> getRooms() {
        return rooms;
    }

    public void setRooms(Map<Long, Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room update(Room room) {
        return daoService.update(room, Table.ROOM);
    }

    @Override
    public Room getById(long roomId) {
        return daoService.getById(roomId, Table.ROOM);
    }

    @Override
    public void save(Room room) {
       daoService.save(room, Table.ROOM);
    }

    @Override
    public List<Room> getAll() {
        return daoService.getAll(Table.ROOM);
    }

    @Override
    public void saveAll(List<Room> rooms) {
        daoService.saveAll(rooms, Table.ROOM);
    }


}
