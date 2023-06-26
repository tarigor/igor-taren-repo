package com.senla.hotel.service;

import com.senla.hotel.entity.GuestServices;
import com.senla.hotel.entity.RoomService;

import java.util.List;

public interface IGuestServicesService {
    //    View the list of guest services and their price (sort by price, by date);
    GuestServices getGuestServicesByPrice(long guestId);

    GuestServices getGuestServicesByDate(long guestId);

    //    Prices of services and rooms (sort by section(category), by price);
    List<GuestServices> getServicePriceByCategoryByPrice(long serviceStatusId);

}
