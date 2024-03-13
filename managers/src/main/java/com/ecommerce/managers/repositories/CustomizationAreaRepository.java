package com.ecommerce.managers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.managers.models.CustomizationArea;

@RepositoryRestResource(path = "customizationArea")
public interface CustomizationAreaRepository extends JpaRepository<CustomizationArea, Long>{


    
}
