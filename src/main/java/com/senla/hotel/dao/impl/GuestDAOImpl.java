package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.EntityDAO;
import com.senla.hotel.dao.IGuestDAO;
import com.senla.hotel.entity.Guest;

import java.util.*;

public class GuestDAOImpl extends EntityDAO implements IGuestDAO {
    private static final GuestDAOImpl INSTANCE = new GuestDAOImpl();
    private final Map<Long, Guest> guests = new HashMap<>();
    private static Set<Long> idHolder = new HashSet<>();

    public static GuestDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Guest> getAll() {
        return new ArrayList<>(guests.values());
    }

    public void setGuests(List<Guest> guests) {
        guests.forEach(this::save);
    }

    @Override
    public Guest getById(long guestId) {
        return guests.get(guestId);
    }

    @Override
    public void save(Guest guest) {
        this.guests.put(generateId(idHolder), guest);
    }


}
