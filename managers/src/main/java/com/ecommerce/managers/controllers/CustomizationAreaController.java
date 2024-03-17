package com.ecommerce.managers.controllers;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.managers.dtos.CustomizationAreaDTO;
import com.ecommerce.managers.models.CustomizationArea;
import com.ecommerce.managers.repositories.CustomizationAreaRepository;

@RepositoryRestController
@RequestMapping("/customizationArea")
public class CustomizationAreaController {

    @Autowired
    private CustomizationAreaRepository customizationAreaRepository;

   /*  @PostMapping("/")
    public @ResponseBody ResponseEntity<String> createCustomizationArea(
            @RequestBody CustomizationAreaDTO customizationArea) {

        if (customizationArea.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Name is required.");
        } else {
            CustomizationArea newCustomizationArea = new CustomizationArea(customizationArea.getName());

            customizationAreaRepository.save(newCustomizationArea);

            return ResponseEntity.status(HttpStatus.SC_CREATED)
                    .body("Customization area created, id: " + newCustomizationArea.getId());
        }
    } */

    @GetMapping("/")
    public @ResponseBody ResponseEntity<Object> getAllCustomizationArea() {

        if (customizationAreaRepository.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No Customization Area found");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(customizationAreaRepository.findAll());
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> getCustomizationAreaById(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required.");
        } else {
            Optional<CustomizationArea> customizationArea = customizationAreaRepository.findById(id);

            if (!customizationArea.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Customization Area not found.");
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(customizationArea.get());
            }
        }

    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateCustomizationArea(@PathVariable("id") Long id,
            @RequestBody CustomizationAreaDTO customizationArea) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required.");
        } else {
            Optional<CustomizationArea> customizationAreaOpt = customizationAreaRepository.findById(id);

            if (!customizationAreaOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Customization Area not found.");
            } else {
                CustomizationArea customizationAreaEntity = customizationAreaOpt.get();

                if (customizationArea.getName() != null) {
                    customizationAreaEntity.setName(customizationArea.getName());
                    customizationAreaRepository.save(customizationAreaEntity);
                }

                return ResponseEntity.status(HttpStatus.SC_OK).body("Customization Area updated, id: " + id);
            }
        }
    }


    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteCustomizationArea(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required.");
        } else {
            Optional<CustomizationArea> customizationArea = customizationAreaRepository.findById(id);

            if (!customizationArea.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Customization Area not found.");
            } else {
                customizationAreaRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Customization Area deleted.");
            }
        }
    }

}
