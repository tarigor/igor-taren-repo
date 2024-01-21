package com.senla.adsdatabase.repository;

import com.senla.adsdatabase.entity.Adv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvRepository extends JpaRepository<Adv, Long> {
}
