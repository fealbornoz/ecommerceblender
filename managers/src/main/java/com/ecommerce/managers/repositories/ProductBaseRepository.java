package com.ecommerce.managers.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.ecommerce.managers.models.ProductBase;

@RepositoryRestResource(path ="productBase")
public interface ProductBaseRepository extends JpaRepository<ProductBase, Long>{



    @RestResource(path = "searchByName")
    List<ProductBase> findByNameContaining(String name);

}
