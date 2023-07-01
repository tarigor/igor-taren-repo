package com.senla.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
    private long roomId;
    private int capacity;
    private double price;
    private boolean roomAvailability;
    private long roomServiceId;
    private int starsRating;
}
