package com.ecommerce.buyers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.buyers.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

    
} 
