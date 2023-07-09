package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.EntityDAO;
import com.senla.hotel.dao.IGuestServicesDAO;
import com.senla.hotel.entity.GuestServices;

import java.util.*;

public class GuestServicesDAOImpl extends EntityDAO implements IGuestServicesDAO {
    private static final GuestServicesDAOImpl INSTANCE = new GuestServicesDAOImpl();
    private static final Set<Long> idHolder = new HashSet<>();
    private final Map<Long, GuestServices> guestServices = new HashMap<>();

    public static GuestServicesDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<GuestServices> getAll() {
        return new ArrayList<>(guestServices.values());
    }

    public void saveAll(List<GuestServices> guestServices) {
        guestServices.forEach(this::save);
    }

    @Override
    public GuestServices getById(long guestId) {
        return guestServices.get(guestId);
    }

    @Override
    public void save(GuestServices guestServices) {
        long id = generateId(idHolder);
        guestServices.setId(id);
        this.guestServices.put(id, guestServices);
    }
}
