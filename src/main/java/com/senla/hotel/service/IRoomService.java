package com.senla.hotel.service;

import com.senla.hotel.constant.ServiceStatus;
import com.senla.hotel.entity.Room;

public interface IRoomService {
    void doCheckIn(int roomId);

    void doCheckOut(int roomId);

    Room changeRoomServiceStatus(int roomId, ServiceStatus serviceStatus);

    Room changeRoomPrice(int roomId, double price);

    Room getRoom(int roomId);

    Room addRoom(Room room);
}
