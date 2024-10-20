package com.davidsolli.dscommerce.services;

import com.davidsolli.dscommerce.DTO.OrderDTO;
import com.davidsolli.dscommerce.entities.Order;
import com.davidsolli.dscommerce.repositories.OrderRepository;
import com.davidsolli.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        return new OrderDTO(order);
    }
}
