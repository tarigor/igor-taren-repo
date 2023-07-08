package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.EntityDAO;
import com.senla.hotel.dao.IGuestServicesDAO;
import com.senla.hotel.entity.GuestServices;

import java.util.*;

public class GuestServicesDAOImpl extends EntityDAO implements IGuestServicesDAO {
    private static final GuestServicesDAOImpl INSTANCE = new GuestServicesDAOImpl();
    private final Map<Long, GuestServices> guestServices = new HashMap<>();
    private static Set<Long> idHolder = new HashSet<>();

    public static GuestServicesDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<GuestServices> getAll() {
        return new ArrayList<>(guestServices.values());
    }

    public void setGuestServices(List<GuestServices> guestServices) {
        guestServices.forEach(this::save);
    }

    @Override
    public GuestServices getByGuestId(long guestId) {
        return guestServices.get(guestId);
    }

    @Override
    public void save(GuestServices guestServices) {
        this.guestServices.put(generateId(idHolder), guestServices);
    }
}
