package com.senla.hoteldb.repository;

import com.senla.hoteldb.entity.RoomService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomServiceRepository extends JpaRepository<RoomService, Long> {
}
