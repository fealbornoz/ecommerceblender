package com.ecommerce.managers.controllers;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import com.ecommerce.managers.dtos.PossibleCustomizationDTO;
import com.ecommerce.managers.models.CustomizationType;
import com.ecommerce.managers.models.PossibleCustomization;
import com.ecommerce.managers.repositories.CustomizationTypeRepository;
import com.ecommerce.managers.repositories.PossibleCustomizationRepository;

@RepositoryRestController
@RequestMapping("/possibleCustomization")
public class PossibleCustomizationController {

    @Autowired
    private PossibleCustomizationRepository possibleCustomizationRepository;

    @Autowired
    private CustomizationTypeRepository customizationTypeRepository;

    /* @PostMapping("/")
    public @ResponseBody ResponseEntity<String> createPossibleCustomization(
            @RequestBody PossibleCustomizationDTO possibleCustomization) {

        if (possibleCustomization.getCustomizationArea() == null
                || possibleCustomization.getCustomizationType() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
                    .body("customization_area_id and customization_type_id are required");
        } else {

            CustomizationArea newCustomizationArea = possibleCustomization.getCustomizationArea();
            CustomizationType newCustomizationType = possibleCustomization.getCustomizationType();

            PossibleCustomization newCustomization = new PossibleCustomization(newCustomizationArea,
                    newCustomizationType);

            possibleCustomizationRepository.save(newCustomization);

            return ResponseEntity.status(HttpStatus.SC_CREATED).body("Possible Customization created");

        }
    } */

    @GetMapping("/")
    public @ResponseBody ResponseEntity<Object> getAllPossibleCustomization() {

        if (possibleCustomizationRepository.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No Possible Customization found");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(possibleCustomizationRepository.findAll());
        }
    }


    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> getPossibleCustomization(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required.");
        } else {
            Optional<PossibleCustomization> possibleCustomization = possibleCustomizationRepository.findById(id);
            if (!possibleCustomization.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Possible Customization not found.");
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(possibleCustomization.get());
            }
        }
    }

   /*  @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updatePossibleCustomization(
            @PathVariable("id") Long id,
            @RequestBody PossibleCustomizationDTO possibleCustomization) {

        if (possibleCustomization.getCustomizationArea() == null
                || possibleCustomization.getCustomizationTypes() == null || id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
                    .body("customization_area_id and customization_type_id are required");
        } else {

            Optional<PossibleCustomization> existingPossibleCustomization = possibleCustomizationRepository
                    .findById(id);

            if (!existingPossibleCustomization.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Possible Customization not found");
            } else {
                PossibleCustomization possibleCustomizationDB = existingPossibleCustomization.get();
                

                possibleCustomizationDB.setCustomizationArea(possibleCustomization.getCustomizationArea());
                
                possibleCustomization.getCustomizationTypes().forEach(customizationType -> {
                        CustomizationType customizationTypeFound = customizationTypeRepository.findById(customizationType.getId()).get();
                        customizationTypeFound.setName(customizationType.getName());
                });

                possibleCustomizationRepository.save(possibleCustomizationDB);

                return ResponseEntity.status(HttpStatus.SC_OK).body("Possible Customization updated");
            }

        }
    } */


    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deletePossibleCustomization(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required.");
        } else {
            Optional<PossibleCustomization> existingPossibleCustomization = possibleCustomizationRepository.findById(id);
            if (!existingPossibleCustomization.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Possible Customization not found.");
            } else {
                possibleCustomizationRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Possible Customization deleted.");
            }
        }
    }



}
