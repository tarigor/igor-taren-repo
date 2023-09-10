package com.senla.hotel.dto;

import com.senla.hotel.entity.Booking;
import com.senla.hotel.entity.Guest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestBookingDTO {
    private static Set<Long> idHolder = new HashSet<>();
    private long id;
    private Guest guest;
    private Booking booking;

    public GuestBookingDTO(Guest guest, Booking booking) {
        generateId();
        this.guest = guest;
        this.booking = booking;
    }

    private void generateId() {
        if (idHolder.isEmpty()) {
            this.id = 1;
        } else {
            this.id = idHolder.size() + 1;
        }
        idHolder.add(id);
    }
}
