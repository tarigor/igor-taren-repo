package com.senla.hotel.entity;

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
@Table(name = "room_service")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RoomService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "service_type", nullable = false, length = 40)
    private String serviceType;

    @Column(name = "price", nullable = false)
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomService that = (RoomService) o;
        return id.equals(that.id) && serviceType.equals(that.serviceType) && price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceType, price);
    }
}