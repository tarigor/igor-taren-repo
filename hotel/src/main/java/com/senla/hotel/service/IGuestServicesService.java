package com.senla.hotel.service;

import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.dto.GuestServicesDTO;

import java.util.List;

public interface IGuestServicesService {
    //    View the list of guest services and their price (sort by price, by date);
    List<GuestServicesDTO> getByGuestIdSorted(long guestId, GuestServicesSection guestServicesSection, Ordering ordering);
}
