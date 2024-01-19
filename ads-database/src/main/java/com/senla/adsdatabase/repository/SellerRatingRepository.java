package com.senla.adsdatabase.repository;

import com.senla.adsdatabase.entity.SellerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRatingRepository extends JpaRepository<SellerRating, Long> {
}
