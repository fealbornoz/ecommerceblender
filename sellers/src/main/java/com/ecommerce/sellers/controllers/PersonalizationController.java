package com.ecommerce.sellers.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.sellers.dtos.PersonalizationDTO;
import com.ecommerce.sellers.models.Personalization;
import com.ecommerce.sellers.models.SelectedCustomizationArea;
import com.ecommerce.sellers.models.SelectedCustomizationType;
import com.ecommerce.sellers.repositories.PersonalizationRepository;

@RepositoryRestController
@RequestMapping("/personalization")
public class PersonalizationController {

    @Autowired
    private PersonalizationRepository personalizationRepository;


    @PostMapping("/")
    public @ResponseBody ResponseEntity<Object> createBulkPersonalization(
            @RequestBody PersonalizationDTO personalization) {

        if (personalization.getName() == null || personalization.getSelectedCustomizationArea() == null
                || personalization.getSelectedCustomizationType() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("All fields are required.");
        } else {

            // Por cada area de personalizacion seleccionada se crea un nuevo objeto con un
            // tipo de personalizacion seleccionado
             List<Personalization> createdPersonalizations = new ArrayList<>();
            for (int i = 0; i < personalization.getSelectedCustomizationArea().size(); i++) {

                SelectedCustomizationArea selectedCustomizationArea = personalization.getSelectedCustomizationArea()
                        .get(i);
                SelectedCustomizationType selectedCustomizationType = personalization.getSelectedCustomizationType()
                        .get(i);

                Personalization newPersonalization = new Personalization(selectedCustomizationArea,
                        selectedCustomizationType);
                personalizationRepository.save(newPersonalization);
                createdPersonalizations.add(newPersonalization);
            }

            return ResponseEntity.status(HttpStatus.SC_CREATED).body(createdPersonalizations);
        }

    }

}
