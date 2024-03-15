package com.ecommerce.sellers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.sellers.models.Store;


@RepositoryRestResource(path = "store")
public interface StoreRepository extends JpaRepository<Store,Long>{

    
}
