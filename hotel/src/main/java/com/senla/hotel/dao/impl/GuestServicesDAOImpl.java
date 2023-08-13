package com.senla.hotel.dao.impl;

import com.senla.hotel.dao.IEntityDAO;
import com.senla.hotel.entity.GuestServices;

import java.util.*;

public class GuestServicesDAOImpl implements IEntityDAO<GuestServices> {
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

    @Override
    public void saveAll(List<GuestServices> guestServices) {
        guestServices.forEach(this::save);
    }

    @Override
    public GuestServices update(GuestServices guestServices) {
        GuestServices updatedGuestServices = getById(guestServices.getGuestId());
        updatedGuestServices.setGuestId(guestServices.getGuestId());
        updatedGuestServices.setServicesOrdered(guestServices.getServicesOrdered());
        return updatedGuestServices;
    }

    @Override
    public GuestServices getById(long guestId) {
        return guestServices.get(guestId);
    }

    @Override
    public void save(GuestServices guestServices) {
        long id;
        if (guestServices.getId() != 0) {
            id = guestServices.getId();
        } else {
            id = generateId(idHolder);
            guestServices.setId(id);
        }
        this.guestServices.put(id, guestServices);
    }
}
