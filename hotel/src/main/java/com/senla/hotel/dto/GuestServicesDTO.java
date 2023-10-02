package com.senla.hotel.dto;

import com.senla.hotel.constant.ServiceType;
import com.senla.hotel.entity.RoomService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestServicesDTO {
    private long id;
    private long guestId;
    private ServiceType serviceType;
    private Date roomServiceOrderDate;
    private double roomServicePrice;

    public GuestServicesDTO(long guestId, ServiceType serviceType, Date roomServiceOrderDate, double roomServicePrice) {
        this.guestId = guestId;
        this.serviceType = serviceType;
        this.roomServiceOrderDate = roomServiceOrderDate;
        this.roomServicePrice = roomServicePrice;
    }
}
