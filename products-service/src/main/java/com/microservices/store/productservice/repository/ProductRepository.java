package com.microservices.store.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.store.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
  
}
