package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.EntityDAO;
import com.senla.hotel.dao.IGuestDAO;
import com.senla.hotel.entity.Guest;

import java.util.*;

public class GuestDAOImpl extends EntityDAO implements IGuestDAO {
    private static final GuestDAOImpl INSTANCE = new GuestDAOImpl();
    private static final Set<Long> idHolder = new HashSet<>();
    private final Map<Long, Guest> guests = new HashMap<>();

    public static GuestDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Guest> getAll() {
        return new ArrayList<>(guests.values());
    }

    public void saveAll(List<Guest> guests) {
        guests.forEach(this::save);
    }

    @Override
    public Guest getById(long guestId) {
        return guests.get(guestId);
    }

    @Override
    public void save(Guest guest) {
        long id = generateId(idHolder);
        guest.setId(id);
        this.guests.put(id, guest);
    }


}
