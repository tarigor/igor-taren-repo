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

import java.util.Objects;

@Entity
@Table(name = "correspondence")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Correspondence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    public Correspondence(User seller, User buyer, String message) {
        this.seller = seller;
        this.buyer = buyer;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correspondence that = (Correspondence) o;
        return id.equals(that.id) && seller.equals(that.seller) && buyer.equals(that.buyer) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seller, buyer, message);
    }
}
