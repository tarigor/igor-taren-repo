package com.senla.hotel.dto.entityexport;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.senla.hotel.deserializationservice.DateToStringConverter;
import com.senla.hotel.deserializationservice.StringToDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate checkInDate;
    @JsonSerialize(converter = DateToStringConverter.class)
    @JsonDeserialize(converter = StringToDateConverter.class)
    private LocalDate checkOutDate;

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

