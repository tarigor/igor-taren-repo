package com.senla.adsdatabase.repository;

import com.senla.adsdatabase.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findSaleByDateIsBetween(Date date, Date date2);
}
