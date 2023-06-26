package com.senla.hotel.dao;

import com.senla.hotel.entity.GuestServices;

import java.util.List;

public interface IGuestServicesDAO {
    List<GuestServices> getGuestServices();
    GuestServices getGuestServicesByGuestId(long guestId);
}
