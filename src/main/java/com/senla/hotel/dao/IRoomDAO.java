package com.senla.hotel.dao;

import com.senla.hotel.entity.Room;

import java.util.List;

public interface IRoomDAO {
    Room update(Room room);

    Room getById(long roomId);

    Room save(Room room);

    List<Room> getAll();
}
