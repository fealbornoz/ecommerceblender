package com.ecommerce.buyers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.buyers.models.ShippingAddress;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

}
