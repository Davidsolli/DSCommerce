package com.davidsolli.dscommerce.repositories;

import com.davidsolli.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
