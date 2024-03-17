package com.ecommerce.buyers.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.buyers.models.OrderItem;




public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{
    
}
