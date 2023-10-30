package com.senla.hoteldb.repository;

import com.senla.hoteldb.entity.GuestServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestServicesRepository extends JpaRepository<GuestServices, Long> {
}
