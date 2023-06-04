package com.senla.hotel.service;

import com.senla.hotel.constant.ServiceStatus;
import com.senla.hotel.entity.Room;

public interface IRoomService {
    boolean doCheckIn(int roomId);
    boolean doCheckOut(int roomId);
    boolean changeRoomServiceStatus(int roomId);
    boolean changeRoomPrice(int roomId, double price);
    Room changeRoomService(int roomId, ServiceStatus serviceStatus);
    Room getRoomInfo(int roomId);
    int addRoom(Room room);
}
