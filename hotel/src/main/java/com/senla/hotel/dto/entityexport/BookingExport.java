package com.senla.hotel.dto.entityexport;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senla.hotel.deserializationService.DateToStringConverter;
import com.senla.hotel.deserializationService.StringToDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingExport implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private long guestId;
    private long bookedRoomId;
    @JsonSerialize(converter = DateToStringConverter.class)
    @JsonDeserialize(converter = StringToDateConverter.class)
    private Date checkInDate;
    @JsonSerialize(converter = DateToStringConverter.class)
    @JsonDeserialize(converter = StringToDateConverter.class)
    private Date checkOutDate;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", guestId=" + guestId +
                ", bookedRoomId=" + bookedRoomId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}

