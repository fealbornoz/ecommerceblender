package com.ecommerce.buyers.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.buyers.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{


    Optional<Cart> findByBuyerId(Long idBuyer);
    
}
