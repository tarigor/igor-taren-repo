package com.senla.hotel.service;

import com.senla.hotel.dto.GuestServicesDto;
import com.senla.hotel.enums.GuestServicesSection;
import com.senla.hotel.enums.Ordering;
import com.senla.hoteldb.entity.GuestServices;

import java.util.ArrayList;
import java.util.List;

public interface IGuestServicesService {
    List<GuestServices> saveAll(List<GuestServices> guestServices);

    //    View the list of guest services and their price (sort by price, by date);
    List<GuestServicesDto> getByGuestIdSorted(long guestId, GuestServicesSection guestServicesSection, Ordering ordering);

    void updateAllAndSaveIfNotExist(ArrayList<GuestServices> guestServicesList);

    List<GuestServices> getAll();

    GuestServicesDto getById(long id);
}
