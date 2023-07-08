package com.senla.hotel.dao;

import com.senla.hotel.entity.GuestServices;

import java.util.List;

public interface IGuestServicesDAO {
    List<GuestServices> getAll();

    GuestServices getByGuestId(long guestId);

    void save(GuestServices guestServices);
}
