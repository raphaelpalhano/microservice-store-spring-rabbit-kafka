package com.mystore.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mystore.orderservice.dto.OrderLineItemsDto;
import com.mystore.orderservice.dto.OrderRequest;
import com.mystore.orderservice.model.Order;
import com.mystore.orderservice.model.OrderLineItems;
import com.mystore.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());


        List<OrderLineItems> orderLineITems = orderRequest.getOrderLineItemsDtoList().stream()
        .map(this::mapOrder)
        .toList();

        order.setOrderLineItemsList(orderLineITems);

        orderRepository.save(order);
    }

    private OrderLineItems mapOrder(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
