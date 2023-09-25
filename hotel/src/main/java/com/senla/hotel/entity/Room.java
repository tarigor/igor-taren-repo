package com.senla.hotel.entity;

import com.senla.container.ConfigProperty;
import com.senla.container.FieldProperty;
import com.senla.hotel.constant.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigProperty(configFileName = "Room")
public class Room {
    private long id;
    @FieldProperty
    private Integer capacity;
    @FieldProperty
    private double price;
    @FieldProperty
    private RoomStatus roomStatus;
    @FieldProperty
    private long roomServiceId;
    @FieldProperty
    private int starsRating;

    public Room(int capacity, double price, RoomStatus roomStatus, long roomServiceId, int starsRating) {
        this.capacity = capacity;
        this.price = price;
        this.roomStatus = roomStatus;
        this.roomServiceId = roomServiceId;
        this.starsRating = starsRating;
    }

    @Override
    public String toString() {
        return "Room{" +
                "capacity=" + capacity +
                ", price=" + price +
                ", roomStatus=" + roomStatus +
                ", roomServiceId=" + roomServiceId +
                ", starsRating=" + starsRating +
                '}';
    }
}
