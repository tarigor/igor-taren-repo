package com.senla.hotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "room")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
}
