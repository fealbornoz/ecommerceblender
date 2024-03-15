package com.ecommerce.managers.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.managers.dtos.PossibleCustomizationDTO;
import com.ecommerce.managers.dtos.ProductBaseDTO;
import com.ecommerce.managers.models.CustomizationArea;
import com.ecommerce.managers.models.CustomizationType;
import com.ecommerce.managers.models.Manager;
import com.ecommerce.managers.models.PossibleCustomization;
import com.ecommerce.managers.models.ProductBase;
import com.ecommerce.managers.repositories.CustomizationAreaRepository;
import com.ecommerce.managers.repositories.CustomizationTypeRepository;
import com.ecommerce.managers.repositories.ManagerRepository;
import com.ecommerce.managers.repositories.PossibleCustomizationRepository;
import com.ecommerce.managers.repositories.ProductBaseRepository;

@RepositoryRestController
// Esta es la ruta general para acceder a este controlador
@RequestMapping("/productBase")
public class ProductBaseController {

    @Autowired
    private ProductBaseRepository productBaseRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private PossibleCustomizationRepository possibleCustomizationRepository;

    @PostMapping("/{id}")
    public @ResponseBody ResponseEntity<String> createProductBase(@PathVariable Long id,
            @RequestBody ProductBaseDTO productBase) {

        if (productBase.getName().isEmpty() || productBase.getDescription().isEmpty() || productBase.getPrice() == null
                || productBase.getManufacturingTime() == null || id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("All fields are required.");
        } else {

            Manager managerFound = managerRepository.findById(id).get();
            ProductBase newProductBase = new ProductBase(productBase.getName(), productBase.getDescription(),
                    productBase.getPrice(), productBase.getManufacturingTime(), managerFound);

            for (PossibleCustomization possibleCustomization : productBase.getPossibleCustomizations()) {

                if (possibleCustomization.getId() != null) {

                    PossibleCustomization newPossibleCustomization = possibleCustomizationRepository
                            .findById(possibleCustomization.getId()).get();
                    newProductBase.getPossibleCustomizations().add(newPossibleCustomization);
                    newPossibleCustomization.getProductBases().add(newProductBase);
                }

            }

            productBaseRepository.save(newProductBase);
            return ResponseEntity.status(HttpStatus.SC_CREATED)
                    .body("Product base created, id: " + newProductBase.getId());
        }
    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<Object> getAllProductBase() {

        if (productBaseRepository.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No product base found");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(productBaseRepository.findAll());
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> getProductBaseById(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<ProductBase> productBase = productBaseRepository.findById(id);
            if (productBase.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_OK).body(productBase.get());
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Product base not found");
            }
        }
    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateProductBase(@PathVariable("id") Long id,
            @RequestBody ProductBaseDTO productBase) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<ProductBase> productBaseToUpdate = productBaseRepository.findById(id);

            if (productBaseToUpdate.isPresent()) {
                ProductBase productBaseUpdated = productBaseToUpdate.get();
                productBaseUpdated.setName(productBase.getName());
                productBaseUpdated.setDescription(productBase.getDescription());
                productBaseUpdated.setPrice(productBase.getPrice());
                productBaseUpdated.setManufacturingTime(productBase.getManufacturingTime());
                productBaseUpdated.setPossibleCustomizations(productBase.getPossibleCustomizations());
                productBaseRepository.save(productBaseUpdated);

                return ResponseEntity.status(HttpStatus.SC_OK)
                        .body("Product base updated, id: " + productBaseUpdated.getId());
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Product base not found");
            }
        }

    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteProductBase(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required.");
        } else {
            Optional<ProductBase> existingProductBase = productBaseRepository.findById(id);
            if (!existingProductBase.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("ProductBase not found.");
            } else {
                productBaseRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.SC_OK).body("ProductBase deleted.");
            }
        }
    }

}
