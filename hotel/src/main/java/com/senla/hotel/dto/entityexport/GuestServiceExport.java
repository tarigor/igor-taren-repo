package com.senla.hotel.dto.entityexport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestServiceExport {
    private long id;
    private long guestId;
    private long roomServiceId;
    private LocalDate roomServiceOrderDate;
}
