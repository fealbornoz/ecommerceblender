package com.ecommerce.buyers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.buyers.models.Buyer;

public interface BuyerRepository  extends JpaRepository<Buyer, Long>{


    
}
