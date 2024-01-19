package com.senla.adsdatabase.repository;

import com.senla.adsdatabase.entity.Correspondence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrespondenceRepository extends JpaRepository<Correspondence, Long> {
}
