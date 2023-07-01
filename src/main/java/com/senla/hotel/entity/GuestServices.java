package com.senla.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
public class GuestServices {
    private long guestId;
    private Map<Date, Long> servicesOrdered;
}
