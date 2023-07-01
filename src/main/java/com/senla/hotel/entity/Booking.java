package com.senla.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Booking {
    private long id;
    private long guestId;
    private long guestServicesId;
    private long bookedRoomId;
    private Date checkInDate;
    private Date checkOutDate;
}
