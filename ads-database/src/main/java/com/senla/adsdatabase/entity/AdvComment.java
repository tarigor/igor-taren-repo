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
@Table(name = "adv_comment")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AdvComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "adv_id", nullable = false)
    private Advertisement advertisement;

    @Column(name = "comment_maker_name")
    private String commentMakerName;

    @Column(name = "comment_text", columnDefinition = "TEXT")
    private String commentText;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvComment that = (AdvComment) o;
        return id.equals(that.id) && advertisement.equals(that.advertisement) && commentMakerName.equals(that.commentMakerName) && commentText.equals(that.commentText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, advertisement, commentMakerName, commentText);
    }
}