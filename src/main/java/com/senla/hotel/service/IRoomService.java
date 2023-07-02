package com.senla.hotel.service;

import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.RoomService;

import java.util.List;

public interface IRoomService {
    void doCheckIn(long roomId);

    void doCheckOut(long roomId);

    Room changeRoomService(long roomId, long serviceStatusId);

    Room changeRoomPrice(long roomId, double price);

    Room getRoom(long roomId);

    Room addRoom(Room room);

    //    List of rooms (sort by price,  by capacity, by number of stars);
    List<Room> findAllOrderedByPrice();

    List<Room> findAllOrderedByCapacity();

    List<Room> findAllOrderedByStars();

    //    List of available rooms (sort by price, by capacity, by number of stars);
    List<Room> findAvailableOrderedByPrice();

    List<Room> findAvailableOrderedByCapacity();

    List<Room> findAvailableOrderedByStars();

    //    Total number of available rooms;
    int findNumberOfAvailableRooms();

    double getRoomPrice(long roomId);

    //
    List<Room> getRoomsOrderedBySection(int sectionNumber);

    List<Room> getRoomsOrderedByPrice();

    List<RoomService> getRoomServicesOrderedByCategory();

    List<RoomService> getRoomServicesOrderedByPrice();
}
