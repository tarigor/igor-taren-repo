package com.senla.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestServicesEntityDTO {
    private long id;
    private long guestId;
    private Map<Date, Long> servicesOrdered = new HashMap<>();

    public GuestServicesEntityDTO(long guestId, Map<Date, Long> servicesOrdered) {
        this.guestId = guestId;
        this.servicesOrdered = servicesOrdered;
    }
}
