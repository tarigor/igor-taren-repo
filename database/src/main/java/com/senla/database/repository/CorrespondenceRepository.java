package com.senla.database.repository;

import com.senla.database.entity.Correspondence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrespondenceRepository extends JpaRepository<Correspondence, Long> {
}
