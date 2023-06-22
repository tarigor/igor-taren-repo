package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IGuestDAO;
import com.senla.hotel.entity.Guest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuestDAOImpl implements IGuestDAO {
    private Map<Long, Guest> guests;

    public void setGuests(Map<Long, Guest> rooms) {
        this.guests = guests;
    }

    @Override
    public List<Guest> getGuests() {
        return new ArrayList<>(guests.values());
    }
}
