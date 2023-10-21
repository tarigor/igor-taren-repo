package com.senla.hoteldb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "room_status", nullable = false, length = 50)
    private String roomStatus;

    @Column(name = "stars_rating", nullable = false)
    private Integer starsRating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id) && capacity.equals(room.capacity) && price.equals(room.price) && roomStatus.equals(room.roomStatus) && starsRating.equals(room.starsRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity, price, roomStatus, starsRating);
    }
}
