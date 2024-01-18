package com.senla.database.entity;

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
@Table(name = "advertisement")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(name = "adv_message", nullable = false, columnDefinition = "TEXT")
    private String advMessage;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return priority == that.priority && id.equals(that.id) && seller.equals(that.seller) && advMessage.equals(that.advMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seller, advMessage, priority);
    }
}
