package com.senla.hotel.entity;

import com.senla.container.ConfigProperty;
import com.senla.container.FieldProperty;
import com.senla.hotel.constant.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigProperty(configFileName = "RoomService")
public class RoomService {
    private long id;
    @FieldProperty
    private ServiceType serviceType;
    @FieldProperty
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
