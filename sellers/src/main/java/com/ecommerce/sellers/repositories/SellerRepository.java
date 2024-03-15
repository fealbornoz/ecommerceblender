package com.ecommerce.sellers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.sellers.models.Seller;


@RepositoryRestResource(path = "seller")
public interface SellerRepository extends JpaRepository<Seller, Long>{
    

}
