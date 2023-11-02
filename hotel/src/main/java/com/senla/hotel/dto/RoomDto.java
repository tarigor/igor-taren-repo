package com.senla.hotel.dto;

import lombok.Data;

@Data
public class RoomDto {
    private Long id;
    private Integer capacity;
    private Double price;
    private String roomStatus;
    private Integer starsRating;
}
