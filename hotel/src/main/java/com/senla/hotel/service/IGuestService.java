package com.senla.hotel.service;

import com.senla.hotel.entity.Guest;

import java.util.ArrayList;
import java.util.List;

public interface IGuestService {
    void saveAll(List<Guest> guests);

    void updateAllAndSaveIfNotExist(ArrayList<Guest> guests);

    List<Guest> getAll();
}
