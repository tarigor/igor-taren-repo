package com.senla.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
public class GuestServicesEntityDTO {
    private long id;
    private long guestId;
    private Map<Date, Long> servicesOrdered;

    public GuestServicesEntityDTO(long guestId, Map<Date, Long> servicesOrdered) {
        this.guestId = guestId;
        this.servicesOrdered = servicesOrdered;
    }
}
