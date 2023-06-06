package com.senla.hotel.entity;

import com.senla.hotel.constant.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
    private int roomId;
    private ServiceStatus serviceStatus;
    private double price;
    private boolean roomAvailability;
}
