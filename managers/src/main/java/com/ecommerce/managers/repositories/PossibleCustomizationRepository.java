package com.ecommerce.managers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.managers.models.PossibleCustomization;

@RepositoryRestResource(path = "possibleCustomization")
public interface PossibleCustomizationRepository extends JpaRepository<PossibleCustomization, Long>{


}
