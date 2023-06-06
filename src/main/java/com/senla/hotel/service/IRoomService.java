package com.senla.hotel.service;

import com.senla.hotel.constant.ServiceStatus;
import com.senla.hotel.entity.Room;

public interface IRoomService {
    boolean doCheckIn(int roomId);

    boolean doCheckOut(int roomId);

    boolean changeRoomServiceStatus(int roomId, ServiceStatus serviceStatus);

    boolean changeRoomPrice(int roomId, double price);

    Room getRoom(int roomId);

    int addRoom(Room room);
}
