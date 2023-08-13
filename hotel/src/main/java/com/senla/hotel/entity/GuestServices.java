package com.senla.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GuestServices {
    private long id;
    private long guestId;
    private String servicesOrdered;

    public GuestServices(long guestId, String servicesOrdered) {
        this.guestId = guestId;
        this.servicesOrdered = servicesOrdered;
    }


}
