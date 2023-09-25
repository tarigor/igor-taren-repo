package com.senla.hotel.entity;

import com.senla.container.ConfigProperty;
import com.senla.container.FieldProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigProperty(configFileName = "Booking")
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    @FieldProperty
    private long guestId;
    @FieldProperty
    private long guestServicesId;
    @FieldProperty
    private long bookedRoomId;
    @FieldProperty
    private Date checkInDate;
    @FieldProperty
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
