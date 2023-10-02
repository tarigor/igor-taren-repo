package com.senla.hotel.entity;

import com.senla.hotel.constant.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomService {
    private long id;
    private ServiceType serviceType;
    private double price;

    public RoomService(ServiceType serviceType, double price) {
        this.serviceType = serviceType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "RoomService{" +
                "id=" + id +
                ", serviceType=" + serviceType +
                ", price=" + price +
                '}';
    }
}