package com.senla.hotel.entity;

import com.senla.hotel.constant.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
    private long roomId;
    private ServiceStatus serviceStatus;
    private int capacity;
    private double price;
    private boolean roomAvailability;
    private int starsRating;
}
