package com.senla.hotel.service;

import com.senla.hoteldb.entity.Guest;

import java.util.ArrayList;
import java.util.List;

public interface IGuestService {
    void saveAll(List<Guest> guests);

    void updateAllAndSaveIfNotExist(ArrayList<Guest> guests);

    List<Guest> getAll();

    Guest getById(Long id);
}
