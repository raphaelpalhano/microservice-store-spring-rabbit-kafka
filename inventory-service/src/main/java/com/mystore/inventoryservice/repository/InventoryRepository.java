package com.mystore.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.inventoryservice.model.Inventory;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    

}
