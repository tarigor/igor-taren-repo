package com.senla.hotel.dto.entityexport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestServiceExport {
    private long id;
    private long guestId;
    private long roomServiceId;
    private Date roomServiceOrderDate;
}
