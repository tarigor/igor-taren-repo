package com.senla.hotel.entity;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class GuestServices {
    private long id;
    private long guestId;
    private Map<Date, Long> servicesOrdered;

    public GuestServices(long guestId, Map<Date, Long> servicesOrdered) {
        this.guestId = guestId;
        this.servicesOrdered = servicesOrdered;
    }
}
