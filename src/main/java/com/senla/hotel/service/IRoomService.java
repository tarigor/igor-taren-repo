package com.senla.hotel.service;

import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.RoomService;

import java.util.List;

public interface IRoomService {
    void doCheckIn(long roomId);

    void doCheckOut(long roomId);

    Room changeRoomServiceStatus(long roomId, long serviceStatusId);

    Room changeRoomPrice(long roomId, double price);

    Room getRoom(long roomId);

    Room addRoom(Room room);

    //    List of rooms (sort by price,  by capacity, by number of stars);
    List<Room> findAllByPrice();

    List<Room> findAllByCapacity();

    List<Room> findAllByStars();

    //    List of available rooms (sort by price, by capacity, by number of stars);
    List<Room> findAvailableByPrice();

    List<Room> findAvailableByCapacity();

    List<Room> findAvailableByStars();

    //    Total number of available rooms;
    int findNumberOfAvailableRooms();

    double getRoomPrice(long roomId);

    //    Prices of services and rooms (sort by section(category), by price);
    public List<Room> getRoomsBySection();

    public List<Room> getRoomsByPrice();

    public List<RoomService> getRoomServicesByCategory();

    public List<RoomService> getRoomServicesByPrice();
}
