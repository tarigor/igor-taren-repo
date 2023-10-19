package com.senla.hotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "guest_service")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class GuestServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    @ToString.Exclude
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_service_id", nullable = false)
    @ToString.Exclude
    private RoomService roomService;

    @Column(name = "room_service_order_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date roomServiceOrderDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestServices that = (GuestServices) o;
        return id.equals(that.id) && guest.equals(that.guest) && roomService.equals(that.roomService) && roomServiceOrderDate.equals(that.roomServiceOrderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guest, roomService, roomServiceOrderDate);
    }
}
