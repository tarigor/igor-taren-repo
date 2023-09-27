package com.senla.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
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

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", guestId=" + guestId +
                ", guestServicesId=" + guestServicesId +
                ", bookedRoomId=" + bookedRoomId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
