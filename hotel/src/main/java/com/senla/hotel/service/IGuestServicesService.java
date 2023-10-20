package com.senla.hotel.service;

import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.dto.GuestServicesDTO;
import com.senla.hoteldb.entity.GuestServices;

import java.util.ArrayList;
import java.util.List;

public interface IGuestServicesService {
    void saveAll(List<GuestServices> guestServices);

    //    View the list of guest services and their price (sort by price, by date);
    List<GuestServicesDTO> getByGuestIdSorted(long guestId, GuestServicesSection guestServicesSection, Ordering ordering);

    void updateAllAndSaveIfNotExist(ArrayList<GuestServices> guestServicesList);

    List<GuestServices> getAll();

    GuestServices getById(long id);
}
