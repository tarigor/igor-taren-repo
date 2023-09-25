package com.senla.hotel.dto;

import com.senla.container.ConfigProperty;
import com.senla.container.FieldProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigProperty(configFileName = "GuestServicesEntityDTO")
public class GuestServicesEntityDTO {
    private long id;
    @FieldProperty
    private long guestId;
    @FieldProperty
    private Map<Date, Long> servicesOrdered;

    public GuestServicesEntityDTO(long guestId, Map<Date, Long> servicesOrdered) {
        this.guestId = guestId;
        this.servicesOrdered = servicesOrdered;
    }
}
