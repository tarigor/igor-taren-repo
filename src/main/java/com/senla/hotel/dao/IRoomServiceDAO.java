package com.senla.hotel.dao;

import com.senla.hotel.entity.RoomService;

import java.util.List;

public interface IRoomServiceDAO {

    List<RoomService> getRoomServices();

    RoomService getRoomServiceById(long serviceId);
    void save(RoomService roomService);
}
