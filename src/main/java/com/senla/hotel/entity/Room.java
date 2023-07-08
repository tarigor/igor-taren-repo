package com.senla.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
    private long id;
    private int capacity;
    private double price;
    private boolean isAvailable;
    private long roomServiceId;
    private int starsRating;
}
