package com.senla.hotel.dao;

import com.senla.hotel.entity.Room;

public interface IRoomDAO {
    Room updateRoom(Room room);
    Room getRoom(int roomId);
    Room addRoom(Room room);
}
