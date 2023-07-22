package com.senla.hotel.entity;

import lombok.Data;

@Data
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
}
