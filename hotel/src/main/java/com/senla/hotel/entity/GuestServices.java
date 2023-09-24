package com.senla.hotel.entity;

import com.senla.container.ConfigProperty;
import com.senla.container.FieldProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigProperty(configFileName = "GuestServices")
public class GuestServices {
    private long id;
    @FieldProperty
    private long guestId;
    @FieldProperty
    private String servicesOrdered;

    public GuestServices(long guestId, String servicesOrdered) {
        this.guestId = guestId;
        this.servicesOrdered = servicesOrdered;
    }


}
