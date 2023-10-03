package com.mystore.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
