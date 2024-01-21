package com.senla.adsdatabase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "adv_id", nullable = false)
    private Adv adv;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Order(User buyer, Adv adv, LocalDate date) {
        this.buyer = buyer;
        this.adv = adv;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) && buyer.equals(order.buyer) && adv.equals(order.adv) && date.equals(order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyer, adv, date);
    }
}