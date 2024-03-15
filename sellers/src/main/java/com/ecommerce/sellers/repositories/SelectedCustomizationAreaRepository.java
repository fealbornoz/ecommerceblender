package com.ecommerce.sellers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.sellers.models.SelectedCustomizationArea;


@RepositoryRestResource(path = "selectedCustomizationArea")
public interface SelectedCustomizationAreaRepository extends JpaRepository<SelectedCustomizationArea, Long>{
    
}
