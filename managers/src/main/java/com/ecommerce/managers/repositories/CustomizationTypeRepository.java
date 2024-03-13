package com.ecommerce.managers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.managers.models.CustomizationType;

@RepositoryRestResource(path = "customizationType")
public interface CustomizationTypeRepository extends JpaRepository<CustomizationType, Long>{


    
}
