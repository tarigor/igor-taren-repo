package com.senla.hotel.service;

import com.senla.hotel.dto.RoomDto;
import com.senla.hotel.enums.Ordering;
import com.senla.hotel.enums.RoomSection;
import com.senla.hotel.exception.HotelModuleException;
import com.senla.hoteldb.entity.Room;

import java.util.ArrayList;
import java.util.List;

public interface IRoomService {
    void saveAll(List<Room> rooms);

    Room changeRoomPrice(long roomId, double price);

    RoomDto getRoom(long roomId);

    RoomDto addRoom(RoomDto room);

    //    List of rooms (sort by price,  by capacity, by number of stars);
    List<RoomDto> getSortedRooms(String sortBy) throws HotelModuleException;

    //    List of available rooms (sort by price, by capacity, by number of stars);
    List<RoomDto> getAvailableSortedRooms(String sortBy, String sortOrder) throws HotelModuleException;

    //    Total number of available rooms;
    int getTotalAvailableRooms();

    double getRoomPrice(long roomId);

    List<RoomDto> getAllOrdered(String roomSectionString, String orderingString) throws HotelModuleException;

    void updateAllAndSaveIfNotExist(ArrayList<Room> rooms);

    List<Room> getAll();
}
