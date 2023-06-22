package com.senla.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Booking {
    private long bookingId;
    private Guest guest;
    private long bookedRoomId;
    private Date checkInTime;
    private Date checkOutDate;

    public Booking(long bookingId, Guest guest, long bookedRoomId) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.bookedRoomId = bookedRoomId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", guest=" + guest +
                ", bookedRoomId=" + bookedRoomId +
                '}';
    }
}
