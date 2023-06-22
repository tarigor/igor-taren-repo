package com.senla.hotel.service;

import com.senla.hotel.constant.ServiceStatus;
import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.RoomService;

import java.util.Date;
import java.util.List;

public interface IRoomService {
    void doCheckIn(long roomId);

    void doCheckOut(long roomId);

    Room changeRoomServiceStatus(long roomId, ServiceStatus serviceStatus);

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
//    List of rooms that will be available on a certain date in the future;
    List<Room> findAvailableRoomsByDate(Date date);
//    View the list of guest services and their price (sort by price, by date);
    List<RoomService> getGuestServicesInfo(long guestId, boolean sortedByPrice, boolean sortedByDate);
//    Prices of services and rooms (sort by section(category), by price);
    List<RoomService> getServicePriceByCategoryByPrice(ServiceStatus serviceStatus);
}
