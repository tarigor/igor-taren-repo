package com.senla.hotel.service;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomSection;
import com.senla.hotel.dto.RoomDto;
import com.senla.hotel.exception.HotelModuleException;
import com.senla.hoteldb.entity.Room;

import java.util.ArrayList;
import java.util.List;

public interface IRoomService {
    void saveAll(List<Room> rooms);

    void doCheckIn(long roomId);

    void doCheckOut(long roomId);

    Room changeRoomPrice(long roomId, double price);

    RoomDto getRoom(long roomId);

    void addRoom(Room room);

    //    List of rooms (sort by price,  by capacity, by number of stars);
    List<RoomDto> getSortedRooms(String sortBy) throws HotelModuleException;

    //    List of available rooms (sort by price, by capacity, by number of stars);
    List<RoomDto> getAvailableSortedRooms(String sortBy, String sortOrder) throws HotelModuleException;

    //    Total number of available rooms;
    int getTotalAvailableRooms();

    double getRoomPrice(long roomId);

    List<Room> getAllOrdered(RoomSection roomSection, Ordering ordering) throws HotelModuleException;

    void updateAllAndSaveIfNotExist(ArrayList<Room> rooms);

    List<Room> getAll();
}
