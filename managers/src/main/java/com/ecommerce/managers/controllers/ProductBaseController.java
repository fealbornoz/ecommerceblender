package com.ecommerce.managers.controllers;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.managers.dtos.ProductBaseDTO;
import com.ecommerce.managers.models.*;
import com.ecommerce.managers.repositories.*;

@RepositoryRestController
// Esta es la ruta general para acceder a este controlador
@RequestMapping("/productBase")
public class ProductBaseController {

    @Autowired
    private ProductBaseRepository productBaseRepository;

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
                productBaseUpdated.update(productBase);
                productBaseRepository.save(productBaseUpdated);

                return ResponseEntity.status(HttpStatus.SC_OK)
                        .body("Product base updated, id: " + productBaseUpdated.getId());
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Product base not found");
            }
        }

    }





}
