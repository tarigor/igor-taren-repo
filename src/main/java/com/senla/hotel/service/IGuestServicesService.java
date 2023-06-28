package com.senla.hotel.service;

import com.senla.hotel.dto.GuestServicesDTO;
import com.senla.hotel.entity.GuestServices;
import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.RoomService;

import java.util.List;

public interface IGuestServicesService {
    //    View the list of guest services and their price (sort by price, by date);
    List<GuestServicesDTO> getGuestServicesByPrice(long guestId);

    List<GuestServicesDTO> getGuestServicesByDate(long guestId);

    //    Prices of services and rooms (sort by section(category), by price);
    public List<Room> getRoomsBySection();
    public List<Room> getRoomsByPrice();
    public List<RoomService> getRoomServicesByCategory();
    public List<RoomService> getRoomServicesByPrice();
}
