package com.ecommerce.managers.controllers;


import java.util.Optional;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.managers.dtos.CustomizationTypeDTO;
import com.ecommerce.managers.models.CustomizationType;
import com.ecommerce.managers.repositories.CustomizationTypeRepository;

@RestController
@RequestMapping("/customizationType")
public class CustomizationTypeController {


    @Autowired
    private CustomizationTypeRepository customizationTypeRepository;


  /*   @PostMapping("/")
    public @ResponseBody ResponseEntity<String> createCustomizationType(
            @RequestBody CustomizationTypeDTO customizationType) {

        if (customizationType.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Name is required.");
        } else {
            CustomizationType newCustomizationType = new CustomizationType(customizationType.getName());

            customizationTypeRepository.save(newCustomizationType);

            return ResponseEntity.status(HttpStatus.SC_CREATED)
                    .body("Customization type created, id: " + newCustomizationType.getId());
        }
    } */


    @GetMapping("/")
    public @ResponseBody ResponseEntity<Object> getAllCustomizationType() {

        if (customizationTypeRepository.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No Customization Type found");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(customizationTypeRepository.findAll());
        }
    }


    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> getCustomizationTypeById(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required.");
        } else {
            Optional<CustomizationType> customizationType = customizationTypeRepository.findById(id);

            if (!customizationType.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Customization Type not found.");
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(customizationType.get());
            }
        }
    }


    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateCustomizationType(@PathVariable("id") Long id,
            @RequestBody CustomizationTypeDTO customizationType) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required.");
        } else {
            Optional<CustomizationType> existingCustomizationType = customizationTypeRepository.findById(id);

            if (!existingCustomizationType.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Customization Type not found.");
            } else {
                CustomizationType customizationTypeEntity = existingCustomizationType.get();
                customizationTypeEntity.setName(customizationType.getName());

                customizationTypeRepository.save(customizationTypeEntity);

                return ResponseEntity.status(HttpStatus.SC_OK).body("Customization Type updated");
            }
        }
    }


    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteCustomizationType(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required.");
        } else {
            Optional<CustomizationType> existingCustomizationType = customizationTypeRepository.findById(id);

            if (!existingCustomizationType.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Customization Type not found.");
            } else {
                customizationTypeRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Customization Type deleted.");
            }
        }
    }


    
}
