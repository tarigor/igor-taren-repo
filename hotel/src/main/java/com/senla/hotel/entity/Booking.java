package com.senla.hotel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Booking {
    private long id;
    private long guestId;
    private long guestServicesId;
    private long bookedRoomId;
    private Date checkInDate;
    private Date checkOutDate;

    public Booking(long guestId, long guestServicesId, long bookedRoomId, Date checkInDate, Date checkOutDate) {
        this.guestId = guestId;
        this.guestServicesId = guestServicesId;
        this.bookedRoomId = bookedRoomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
}
