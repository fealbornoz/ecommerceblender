package com.ecommerce.sellers.controllers;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.sellers.models.Personalization;
import com.ecommerce.sellers.repositories.PersonalizationRepository;

@RestController
@RequestMapping("/personalization")
public class PersonalizationController {

    @Autowired
    private PersonalizationRepository personalizationRepository;


   /*  @PostMapping("/")
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

    } */


    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deletePersonalization(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id is required");
        } else {
            Optional<Personalization> personalizationToDelete = personalizationRepository.findById(id);
            if (personalizationToDelete.isPresent()) {
                personalizationRepository.delete(personalizationToDelete.get());
                return ResponseEntity.status(HttpStatus.SC_OK).body("Personalization deleted");
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Personalization not found");
            }
        }
    }
   





}
