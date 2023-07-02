package com.senla.hotel.entity;

import com.senla.hotel.constant.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomService {
    private long id;
    private ServiceType serviceType;
    private double price;
}
