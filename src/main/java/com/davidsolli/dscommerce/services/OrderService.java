package com.davidsolli.dscommerce.services;

import com.davidsolli.dscommerce.DTO.OrderDTO;
import com.davidsolli.dscommerce.DTO.OrderItemDTO;
import com.davidsolli.dscommerce.entities.*;
import com.davidsolli.dscommerce.repositories.OrderItemRepository;
import com.davidsolli.dscommerce.repositories.OrderRepository;
import com.davidsolli.dscommerce.repositories.ProductRepository;
import com.davidsolli.dscommerce.services.exceptions.ForbiddenException;
import com.davidsolli.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) throws ForbiddenException {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        authService.validateSelfAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();
        User user = userService.authenticated();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(user);

        for (OrderItemDTO itemDTO: dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItens().add(item);
        }

        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItens());

        return new OrderDTO(order);
    }
}
