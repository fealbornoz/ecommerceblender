package com.ecommerce.sellers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.sellers.models.FinalProduct;

@RepositoryRestResource(path = "publication")
public interface FinalProductRepository extends JpaRepository<FinalProduct, Long> {

}
