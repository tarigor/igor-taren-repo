package com.senla.hotel.dto;

import com.senla.hotel.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestServicesDto {
    private long id;
    private long guestId;
    private ServiceType serviceType;
    private LocalDate roomServiceOrderDate;
    private double roomServicePrice;

    public GuestServicesDto(long guestId, ServiceType serviceType, LocalDate roomServiceOrderDate, double roomServicePrice) {
        this.guestId = guestId;
        this.serviceType = serviceType;
        this.roomServiceOrderDate = roomServiceOrderDate;
        this.roomServicePrice = roomServicePrice;
    }
}
