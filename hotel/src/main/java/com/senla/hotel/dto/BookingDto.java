package com.senla.hotel.dto;

import com.senla.hoteldb.entity.Guest;
import com.senla.hoteldb.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookingDto {
    private Long id;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
