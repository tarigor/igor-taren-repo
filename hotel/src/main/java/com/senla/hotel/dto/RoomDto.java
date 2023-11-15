package com.senla.hotel.dto;

import com.senla.hotel.enums.RoomStatus;
import com.senla.hotel.validator.annotation.EnumValidator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomDto {
    private long id;
    private Integer capacity;
    private Double price;
    @EnumValidator(targetClassType = RoomStatus.class)
    private String roomStatus;
    private Integer starsRating;
}
