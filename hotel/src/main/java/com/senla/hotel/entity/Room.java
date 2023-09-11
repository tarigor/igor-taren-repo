package com.senla.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private long id;
    private int capacity;
    private double price;
    private boolean isAvailable;
    private long roomServiceId;
    private int starsRating;

    public Room(int capacity, double price, boolean isAvailable, long roomServiceId, int starsRating) {
        this.capacity = capacity;
        this.price = price;
        this.isAvailable = isAvailable;
        this.roomServiceId = roomServiceId;
        this.starsRating = starsRating;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", roomServiceId=" + roomServiceId +
                ", starsRating=" + starsRating +
                '}';
    }
}
