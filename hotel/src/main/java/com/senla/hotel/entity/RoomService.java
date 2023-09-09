package com.senla.hotel.entity;

import com.senla.hotel.constant.ServiceType;
import lombok.Data;

@Data
public class RoomService {
    private long id;
    private ServiceType serviceType;
    private double price;

    public RoomService(ServiceType serviceType, double price) {
        this.serviceType = serviceType;
        this.price = price;
    }
}
