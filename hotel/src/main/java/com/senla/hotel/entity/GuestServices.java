package com.senla.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestServices {
    private long id;
    private long guestId;
    private long roomServiceId;
    private Date roomServiceOrderDate;

    public GuestServices(long guestId, long roomServiceId, Date roomServiceOrderDate) {
        this.guestId = guestId;
        this.roomServiceId = roomServiceId;
        this.roomServiceOrderDate = roomServiceOrderDate;
    }
}
