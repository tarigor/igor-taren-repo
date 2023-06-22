package com.senla.hotel.dao;

import com.senla.hotel.entity.Room;

import java.util.List;

public interface IRoomDAO {
    Room updateRoom(Room room);
    Room getRoom(long roomId);
    Room addRoom(Room room);
    List<Room> getRooms();
}
