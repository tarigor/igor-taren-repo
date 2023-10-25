package com.senla.hotel.service;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomSection;
import com.senla.hotel.exception.HotelModuleException;
import com.senla.hoteldb.entity.Room;

import java.util.ArrayList;
import java.util.List;

public interface IRoomService {
    void saveAll(List<Room> rooms);

    void doCheckIn(long roomId);

    void doCheckOut(long roomId);

    Room changeRoomPrice(long roomId, double price);

    Room getRoom(long roomId);

    void addRoom(Room room);

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

    List<Room> getAllOrdered(RoomSection roomSection, Ordering ordering) throws HotelModuleException;

    void updateAllAndSaveIfNotExist(ArrayList<Room> rooms);

    List<Room> getAll();
}
