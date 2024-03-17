package com.ecommerce.sellers.controllers;



import java.util.Optional;

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


    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateSelectedCustomizationType(@PathVariable Long id, @RequestBody SelectedCustomizationTypeDTO selectedCustomizationTypeDTO) {

        if (id == null || selectedCustomizationTypeDTO == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id and selectedCustomizationTypeDTO are required");
        } else {
            Optional<SelectedCustomizationType> selectedCustomizationTypeToUpdate = selectedCustomizationTypeRepository.findById(id);
            if (selectedCustomizationTypeToUpdate.isPresent()) {
                selectedCustomizationTypeToUpdate.get().setName(selectedCustomizationTypeDTO.getName());
                selectedCustomizationTypeRepository.save(selectedCustomizationTypeToUpdate.get());
                return ResponseEntity.status(HttpStatus.SC_OK).body("SelectedCustomizationType updated");
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("SelectedCustomizationType not found");
            }
        }
    }


    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteSelectedCustomizationType(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id is required");
        } else {
            Optional<SelectedCustomizationType> selectedCustomizationTypeToDelete = selectedCustomizationTypeRepository.findById(id);
            if (selectedCustomizationTypeToDelete.isPresent()) {
                selectedCustomizationTypeRepository.delete(selectedCustomizationTypeToDelete.get());
                return ResponseEntity.status(HttpStatus.SC_OK).body("SelectedCustomizationType deleted");
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("SelectedCustomizationType not found");
            }
        }
    }

    


    

}
