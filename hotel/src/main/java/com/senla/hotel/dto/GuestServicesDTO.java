package com.senla.hotel.dto;

import com.senla.hotel.constant.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
