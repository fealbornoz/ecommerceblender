package com.ecommerce.sellers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.sellers.models.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long> {

}
