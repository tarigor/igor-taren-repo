package com.senla.hotel.service;

import com.senla.hotel.constant.ServiceStatus;
import com.senla.hotel.entity.Guest;
import com.senla.hotel.entity.GuestDates;
import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.Service;

import java.util.Date;
import java.util.List;

public interface IRoomService {
    void doCheckIn(long roomId);

    void doCheckOut(long roomId);

    Room changeRoomServiceStatus(long roomId, ServiceStatus serviceStatus);

    Room changeRoomPrice(long roomId, double price);

    Room getRoom(long roomId);

    Room addRoom(Room room);

//    Electronic hotel administrator.
//    The program should allow you to show:
//
//    List of rooms (sort by price,  by capacity, by number of stars);
    List<Room> findAllByPrice();
    List<Room> findAllByCapacity();
    List<Room> findAllByStars();
//    List of available rooms (sort by price, by capacity, by number of stars);
    List<Room> findAvailableByPrice();
    List<Room> findAvailableByCapacity();
    List<Room> findAvailableByStars();
//    List of guests and their rooms (sort alphabetically and by check-out date);
    List<Guest> findAllSortByAlphAndCheckout();
//    Total number of available rooms;
    int findNumberOfAvailableRooms();
//    Total number of guests;
    int findCountOfAllGuests();
//    List of rooms that will be available on a certain date in the future;
    List<Room> findAvailableRoomsByDate(Date date);
//    The amount of payment for the room to be paid by the guest;
    double getTotalPaymentByGuest(long guestId);
//    View the last 3 guests of the room and the dates of their stay;
    List<GuestDates> findLastGuestOfRoomAndDates(int countOfGuests, long roomId);
//    View the list of guest services and their price (sort by price, by date);
    List<Service> getGuestServicesInfo(long guestId, boolean sortedByPrice, boolean sortedByDate);
//    Prices of services and rooms (sort by section(category), by price);
    List<Service> getServicePriceByCategoryByPrice(ServiceStatus serviceStatus);
//    Show the details of a separate room.
//    The program should provide the opportunity to: (already done)
//    Check in to the room;
//    Check out from the room;
//    Change the status of the room to repaired/serviced;
//    Change the price of a room or service;
//    Add a room or service.
}
