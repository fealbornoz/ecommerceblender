package com.ecommerce.sellers.controllers;

import java.util.Optional;

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
    
    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateSelectedCustomizationArea(@PathVariable Long id, @RequestBody SelectedCustomizationAreaDTO selectedCustomizationAreaDTO) {

        if (id == null || selectedCustomizationAreaDTO == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id and selectedCustomizationAreaDTO are required");
        } else {
            Optional<SelectedCustomizationArea> selectedCustomizationAreaToUpdate = selectedCustomizationAreaRepository.findById(id);
            if (selectedCustomizationAreaToUpdate.isPresent()) {
                selectedCustomizationAreaToUpdate.get().setName(selectedCustomizationAreaDTO.getName());
                selectedCustomizationAreaRepository.save(selectedCustomizationAreaToUpdate.get());
                return ResponseEntity.status(HttpStatus.SC_OK).body("SelectedCustomizationArea updated");
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("SelectedCustomizationArea not found");
            }
        }
    }


    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteSelectedCustomizationArea(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id is required");
        } else {
            Optional<SelectedCustomizationArea> selectedCustomizationAreaToDelete = selectedCustomizationAreaRepository.findById(id);
            if (selectedCustomizationAreaToDelete.isPresent()) {
                selectedCustomizationAreaRepository.delete(selectedCustomizationAreaToDelete.get());
                return ResponseEntity.status(HttpStatus.SC_OK).body("SelectedCustomizationArea deleted");
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("SelectedCustomizationArea not found");
            }
        }
    }

}
