package com.senla.hotel.dto;

import com.senla.hotel.entity.RoomService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestServicesDTO {
    private static Set<Long> idHolder = new HashSet<>();
    private long id;
    private Date date;
    private RoomService roomService;

    public GuestServicesDTO(Date date, RoomService roomService) {
        generateId();
        this.date = date;
        this.roomService = roomService;
    }

    private void generateId() {
        if (idHolder.isEmpty()) {
            this.id = 1;
        } else {
            this.id = idHolder.size() + 1;
        }
        idHolder.add(id);
    }

    @Override
    public String toString() {
        return "GuestServicesDTO{" +
                "date=" + new SimpleDateFormat("MM-dd-yyyy").format(date) +
                ", roomService=" + roomService +
                '}';
    }
}
