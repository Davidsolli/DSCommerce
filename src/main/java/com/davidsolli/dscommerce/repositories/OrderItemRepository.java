package com.davidsolli.dscommerce.repositories;

import com.davidsolli.dscommerce.entities.OrderItem;
import com.davidsolli.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
