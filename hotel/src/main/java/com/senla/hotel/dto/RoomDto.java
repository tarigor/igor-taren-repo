package com.senla.hotel.dto;

import com.senla.hotel.constant.RoomStatus;
import com.senla.hotel.validator.annotation.EnumValidator;
import lombok.Data;

@Data
public class RoomDto {
    private long id;
    private Integer capacity;
    private Double price;
    @EnumValidator(targetClassType = RoomStatus.class)
    private String roomStatus;
    private Integer starsRating;
}
