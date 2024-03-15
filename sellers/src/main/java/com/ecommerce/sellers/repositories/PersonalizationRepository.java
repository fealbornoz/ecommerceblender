package com.ecommerce.sellers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.sellers.models.Personalization;

@RepositoryRestResource(path = "personalization")
public interface PersonalizationRepository extends JpaRepository<Personalization, Long>{

    
    
}
