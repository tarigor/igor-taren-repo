package com.senla.hotel.service;

import com.senla.hotel.dto.GuestServicesDTO;

import java.util.List;

public interface IGuestServicesService {
    //    View the list of guest services and their price (sort by price, by date);
    List<GuestServicesDTO> getGuestServicesByPrice(long guestId);

    List<GuestServicesDTO> getGuestServicesByDate(long guestId);
}
