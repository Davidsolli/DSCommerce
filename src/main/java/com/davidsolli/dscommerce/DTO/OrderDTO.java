package com.davidsolli.dscommerce.DTO;

import com.davidsolli.dscommerce.entities.Order;
import com.davidsolli.dscommerce.entities.OrderItem;
import com.davidsolli.dscommerce.entities.OrderStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;

    private ClientDTO clientDTO;

    private PaymentDTO paymentDTO;

    @NotEmpty(message = "Deve ter pelo menos um item")
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO clientDTO, PaymentDTO paymentDTO) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.clientDTO = clientDTO;
        this.paymentDTO = paymentDTO;
    }

    public OrderDTO(Order order) {
        id = order.getId();
        moment = order.getMoment();
        status = order.getStatus();
        clientDTO = new ClientDTO(order.getClient());
        paymentDTO = (order.getPayment() == null) ? null : new PaymentDTO(order.getPayment());
        for (OrderItem item: order.getItens()) {
            OrderItemDTO itemDTO = new OrderItemDTO(item);
            items.add(itemDTO);
        }
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (OrderItemDTO item: items) sum += item.getSubTotal();
        return sum;
    }
}
