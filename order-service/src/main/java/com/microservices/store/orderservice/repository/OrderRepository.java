package com.microservices.store.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.store.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
