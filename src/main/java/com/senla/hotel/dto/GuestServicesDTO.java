package com.senla.hotel.dto;

import com.senla.hotel.entity.RoomService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
public class GuestServicesDTO {
    private Date date;
    private RoomService roomService;

    @Override
    public String toString() {
        return "GuestServicesDTO{" +
                "date=" + new SimpleDateFormat("MM-dd-yyyy").format(date) +
                ", roomService=" + roomService +
                '}';
    }
}
