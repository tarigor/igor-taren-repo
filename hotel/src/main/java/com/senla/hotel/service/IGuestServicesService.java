package com.senla.hotel.service;

import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.dto.GuestServicesDTO;
import com.senla.hotel.dto.GuestServicesEntityDTO;
import com.senla.hotel.entity.GuestServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IGuestServicesService {
    void saveAll(Map<Long, GuestServices> guestServices);

    //    View the list of guest services and their price (sort by price, by date);
    List<GuestServicesDTO> getByGuestIdSorted(long guestId, GuestServicesSection guestServicesSection, Ordering ordering);

    void updateAllAndSaveIfNotExist(ArrayList<GuestServices> guestServicesList);

    List<GuestServicesEntityDTO> getAll();

    GuestServicesEntityDTO getById(long id);

}
