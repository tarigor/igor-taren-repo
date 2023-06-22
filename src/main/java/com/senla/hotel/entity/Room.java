package com.senla.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
    private long roomId;
    private RoomService roomService;
    private int capacity;
    private double price;
    private boolean roomAvailability;
    private int starsRating;
}
