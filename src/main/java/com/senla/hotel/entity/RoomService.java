package com.senla.hotel.entity;

import com.senla.hotel.constant.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomService {
    private long roomServiceId;
    private ServiceStatus serviceStatus;
    private double price;
}
