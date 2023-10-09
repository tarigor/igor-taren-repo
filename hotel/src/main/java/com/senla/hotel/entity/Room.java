package com.senla.hotel.entity;

import com.senla.hotel.constant.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private long id;
    private Integer capacity;
    private double price;
    private RoomStatus roomStatus;
    private int starsRating;

    public Room(int capacity, double price, RoomStatus roomStatus, int starsRating) {
        this.capacity = capacity;
        this.price = price;
        this.roomStatus = roomStatus;
        this.starsRating = starsRating;
    }

    @Override
    public String toString() {
        return "Room{" +
                "capacity=" + capacity +
                ", price=" + price +
                ", roomStatus=" + roomStatus +
                ", starsRating=" + starsRating +
                '}';
    }
}
