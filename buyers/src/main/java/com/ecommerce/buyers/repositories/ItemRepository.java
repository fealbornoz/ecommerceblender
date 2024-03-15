package com.ecommerce.buyers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.buyers.models.Item;


public interface ItemRepository extends JpaRepository<Item, Long>{



    
}
