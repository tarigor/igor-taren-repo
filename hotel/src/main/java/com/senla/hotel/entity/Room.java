package com.senla.hotel.entity;

import com.senla.container.ConfigProperty;
import com.senla.container.FieldProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigProperty(configFileName = "Room")
public class Room {
    private long id;
    @FieldProperty
    private Integer capacity;
    @FieldProperty
    private double price;
    @FieldProperty
    private boolean isAvailable;
    @FieldProperty
    private long roomServiceId;
    @FieldProperty
    private int starsRating;

    public Room(int capacity, double price, boolean isAvailable, long roomServiceId, int starsRating) {
        this.capacity = capacity;
        this.price = price;
        this.isAvailable = isAvailable;
        this.roomServiceId = roomServiceId;
        this.starsRating = starsRating;
    }

    @Override
    public String toString() {
        return "Room{" +
                "capacity=" + capacity +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", roomServiceId=" + roomServiceId +
                ", starsRating=" + starsRating +
                '}';
    }
}
