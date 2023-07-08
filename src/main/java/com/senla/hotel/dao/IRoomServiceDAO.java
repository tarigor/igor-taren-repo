package com.senla.hotel.dao;

import com.senla.hotel.entity.RoomService;

import java.util.List;

public interface IRoomServiceDAO {

    List<RoomService> getAll();

    RoomService getById(long serviceId);

    void save(RoomService roomService);
}
