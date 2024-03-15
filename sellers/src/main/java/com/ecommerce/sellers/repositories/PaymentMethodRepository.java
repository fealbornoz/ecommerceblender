package com.ecommerce.sellers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.sellers.models.PaymentMethod;

public interface PaymentMethodRepository  extends JpaRepository<PaymentMethod, Long>{
    
}
