package com.ecommerce.sellers.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.sellers.dtos.SelectedCustomizationTypeDTO;
import com.ecommerce.sellers.models.SelectedCustomizationType;
import com.ecommerce.sellers.repositories.SelectedCustomizationTypeRepository;

@RepositoryRestController
@RequestMapping("/selectedCustomizationType")
public class SelectedCustomizationTypeController {

    @Autowired
    private SelectedCustomizationTypeRepository selectedCustomizationTypeRepository;

    @PostMapping("/")
    public @ResponseBody ResponseEntity<Object> createBulkPersonalization(
            @RequestBody SelectedCustomizationTypeDTO selectedCustomizationType) {

        if (selectedCustomizationType.getNames() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("All fields are required.");
        } else {

            // Por cada Type de personalizacion seleccionada se crea un nuevo objeto con un
            // tipo de personalizacion seleccionado
            List<SelectedCustomizationType> createdSelectedCustomizationTypes = new ArrayList<>();
            for (int i = 0; i < selectedCustomizationType.getNames().size(); i++) {

                SelectedCustomizationType newSelectedCustomizationType = new SelectedCustomizationType(
                        selectedCustomizationType.getNames().get(i));
                selectedCustomizationTypeRepository.save(newSelectedCustomizationType);
            }

            return ResponseEntity.status(HttpStatus.SC_CREATED).body(createdSelectedCustomizationTypes);
        }

    }

}
