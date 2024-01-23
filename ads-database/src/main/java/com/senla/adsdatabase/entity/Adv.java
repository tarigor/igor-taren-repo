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

@Entity
@Table(name = "adv")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Adv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(name = "adv_message", nullable = false, columnDefinition = "TEXT")
    private String advMessage;

    @Column(name = "priority", nullable = false)
    private boolean priority;

    public Adv(User seller, String advMessage, boolean priority) {
        this.seller = seller;
        this.advMessage = advMessage;
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adv adv = (Adv) o;

        if (priority != adv.priority) return false;
        if (!id.equals(adv.id)) return false;
        if (!seller.equals(adv.seller)) return false;
        return advMessage.equals(adv.advMessage);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + seller.hashCode();
        result = 31 * result + advMessage.hashCode();
        result = 31 * result + (priority ? 1 : 0);
        return result;
    }
}
