package com.ecommerce.sellers.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.sellers.dtos.SelectedCustomizationAreaDTO;
import com.ecommerce.sellers.models.SelectedCustomizationArea;
import com.ecommerce.sellers.repositories.SelectedCustomizationAreaRepository;

@RepositoryRestController
@RequestMapping("/selectedCustomizationArea")
public class SelectedCustomizationAreaController {

    @Autowired
    private SelectedCustomizationAreaRepository selectedCustomizationAreaRepository;

    @PostMapping("/")
    public @ResponseBody ResponseEntity<Object> createBulkPersonalization(
            @RequestBody SelectedCustomizationAreaDTO selectedCustomizationArea) {

        if (selectedCustomizationArea.getNames() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("All fields are required.");
        } else {

            // Por cada area de personalizacion seleccionada se crea un nuevo objeto con un
            // tipo de personalizacion seleccionado
            List<SelectedCustomizationArea> createdSelectedCustomizationAreas = new ArrayList<>();
            for (int i = 0; i < selectedCustomizationArea.getNames().size(); i++) {

                SelectedCustomizationArea newSelectedCustomizationArea = new SelectedCustomizationArea(
                        selectedCustomizationArea.getNames().get(i));
                selectedCustomizationAreaRepository.save(newSelectedCustomizationArea);
            }

            return ResponseEntity.status(HttpStatus.SC_CREATED).body(createdSelectedCustomizationAreas);
        }

    }

}
