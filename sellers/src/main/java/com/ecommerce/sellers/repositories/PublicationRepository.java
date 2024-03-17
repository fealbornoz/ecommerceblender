package com.ecommerce.sellers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.sellers.models.Publication;

@RepositoryRestResource(path = "publication")
public interface PublicationRepository extends JpaRepository<Publication, Long>{


    List<Publication> findBySellerId(Long sellerId);
    
}
