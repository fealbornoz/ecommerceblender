package com.ecommerce.managers.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ecommerce.managers.models.Manager;

@RepositoryRestResource(path ="managerRepository")
public interface ManagerRepository extends JpaRepository<Manager, Long>{
    

}


