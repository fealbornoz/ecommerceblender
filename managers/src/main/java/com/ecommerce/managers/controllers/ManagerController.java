package com.ecommerce.managers.controllers;

import java.util.*;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.managers.dtos.ManagerDTO;
import com.ecommerce.managers.dtos.ProductBaseDTO;
import com.ecommerce.managers.models.Manager;
import com.ecommerce.managers.models.ProductBase;
import com.ecommerce.managers.repositories.ManagerRepository;
import com.ecommerce.managers.repositories.ProductBaseRepository;
import com.ecommerce.managers.services.ProductService;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ProductBaseRepository productBaseRepository;


    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public @ResponseBody ResponseEntity<String> createManager(@RequestBody ManagerDTO manager) {

        if (manager.getName().isEmpty() || manager.getLastName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Name and last name are required.");
        } else {
            Manager newManager = new Manager(manager.getName(), manager.getLastName());

            managerRepository.save(newManager);

            return ResponseEntity.status(HttpStatus.SC_CREATED).body("Manager created, id: " + newManager.getId());
        }
    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<Object> getAllManagers() {

        if (managerRepository.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No managers found");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(managerRepository.findAll());
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> getManagerById(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<Manager> manager = managerRepository.findById(id);

            if (manager.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Manager not found");
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(manager);
            }
        }

    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateManager(@PathVariable("id") Long id,
            @RequestBody ManagerDTO manager) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<Manager> existingManager = managerRepository.findById(id);

            if (existingManager.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Manager not found");
            } else {
                Manager managerToUpdate = existingManager.get();
                managerToUpdate.setName(manager.getName());
                managerToUpdate.setLastName(manager.getLastName());

                managerRepository.save(managerToUpdate);

                return ResponseEntity.status(HttpStatus.SC_OK).body("Manager updated");
            }
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteManager(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<Manager> existingManager = managerRepository.findById(id);

            if (existingManager.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Manager not found");
            } else {

                managerRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Manager deleted");
            }
        }
    }



    @PostMapping("/{idManager}/productBase")
    public @ResponseBody ResponseEntity<String> createProductBase(@PathVariable("idManager") Long id,
            @RequestBody ProductBaseDTO productBase) {

        if (productBase.getName().isEmpty() || productBase.getDescription().isEmpty() || productBase.getPrice() == null
                || productBase.getManufacturingTime() == null || id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("All fields are required.");
        } else {
            ProductBase newProductBase = productService.createProductBaseAndCustomizations(id, productBase);
            return ResponseEntity.status(HttpStatus.SC_CREATED)
                    .body("Product base created, id: " + newProductBase.getId());
        }
    }

    /* @PostMapping("/{idManager}/productBase")
    public @ResponseBody ResponseEntity<String> createProductBase(@PathVariable("idManager") Long id,
            @RequestBody ProductBaseDTO productBase) {

        if (productBase.getName().isEmpty() || productBase.getDescription().isEmpty() || productBase.getPrice() == null
                || productBase.getManufacturingTime() == null || id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("All fields are required.");
        } else {


            List<PossibleCustomization> possibleCustomizations = productBase.getPossibleCustomizations().stream()
                    .map(customizationDTO -> {
                        PossibleCustomization possibleCustomization = new PossibleCustomization(
                                customizationDTO.getCustomizationArea());

                        customizationDTO.getCustomizationTypes().stream()
                                .forEach(customizationTypeDTO -> {
                                    CustomizationType customizationType = new CustomizationType(
                                            customizationTypeDTO.getName());
                                    possibleCustomization.addCustomizationType(customizationType);
                                });

                        return possibleCustomizationRepository.save(possibleCustomization);
                    })
                    .collect(Collectors.toList());

            ProductBase newProductBase = new ProductBase(productBase.getName(), productBase.getDescription(),
                    productBase.getPrice(), productBase.getManufacturingTime());

            possibleCustomizations.stream().forEach(possibleCustomization -> {
                newProductBase.addPossibleCustomization(possibleCustomization);
            });

            Manager managerFound = managerRepository.findById(id).get();
            managerFound.addProductBase(newProductBase);

            managerRepository.save(managerFound);

            return ResponseEntity.status(HttpStatus.SC_CREATED)
                    .body("Product base created, id: " + newProductBase.getId());
        }
    } */




    @DeleteMapping("/{idManager}/productBase/{idProductBase}")
    public @ResponseBody ResponseEntity<String> deleteProductBase(@PathVariable("idManager") Long idManager,
            @PathVariable("idProductBase") Long idProductBase) {

        if (idManager == null || idProductBase == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<Manager> managerOptional = managerRepository.findById(idManager);
            Optional<ProductBase> productBaseOptional = productBaseRepository.findById(idProductBase);
            if (managerOptional.isPresent() && productBaseOptional.isPresent()) {
                Manager manager = managerOptional.get();
                ProductBase productBase = productBaseOptional.get();
                manager.removeProductBase(productBase);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Product base deleted");
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Product base not found");
            }
        }
    }

}
