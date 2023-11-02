package com.senla.hotel.dto;

import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.Room;
import lombok.Data;

import java.util.Date;

@Data
public class BookingDto {
    private Long id;
    private Guest guest;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
}
