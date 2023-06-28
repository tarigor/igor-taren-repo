package com.senla.hotel.dto;

import com.senla.hotel.entity.Room;
import com.senla.hotel.entity.RoomService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServicesAndRoomsDTO {
    private RoomService roomService;
    private Room room;

    @Override
    public String toString() {
        return "ServicesAndRoomsDTO{" +
                "roomService ID=" + roomService.getRoomServiceId() + "roomService name=" + roomService.getServiceStatus().name() +
                ", room ID=" + room.getRoomId() + " room price=" + room.getPrice() +
                '}';
    }
}
