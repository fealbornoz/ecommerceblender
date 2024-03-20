package com.ecommerce.sellers.controllers;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.sellers.dtos.SelectedCustomizationTypeDTO;
import com.ecommerce.sellers.models.SelectedCustomizationType;
import com.ecommerce.sellers.repositories.SelectedCustomizationTypeRepository;

@RestController
@RequestMapping("/selectedCustomizationType")
public class SelectedCustomizationTypeController {

    @Autowired
    private SelectedCustomizationTypeRepository selectedCustomizationTypeRepository;

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateSelectedCustomizationType(@PathVariable("id") Long id,
            @RequestBody SelectedCustomizationTypeDTO selectedCustomizationTypeDTO) {

        if (id == null || selectedCustomizationTypeDTO.getName() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
                    .body("id and selectedCustomizationTypeDTO are required");
        } else {
            Optional<SelectedCustomizationType> selectedCustomizationTypeToUpdate = selectedCustomizationTypeRepository
                    .findById(id);
            if (!selectedCustomizationTypeToUpdate.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("SelectedCustomizationType not found");
            } else {
                SelectedCustomizationType selectedCustomizationType = selectedCustomizationTypeToUpdate.get();
                selectedCustomizationType.setName(selectedCustomizationTypeDTO.getName());
                selectedCustomizationTypeRepository.save(selectedCustomizationType);
                return ResponseEntity.status(HttpStatus.SC_OK).body("SelectedCustomizationType updated");

            }
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteSelectedCustomizationType(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id is required");
        } else {
            Optional<SelectedCustomizationType> selectedCustomizationTypeToDelete = selectedCustomizationTypeRepository
                    .findById(id);
            if (!selectedCustomizationTypeToDelete.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("SelectedCustomizationType not found");
            } else {
                SelectedCustomizationType selectedCustomizationType = selectedCustomizationTypeToDelete.get();
                if(selectedCustomizationType != null) {
                selectedCustomizationTypeRepository.delete(selectedCustomizationType);
                return ResponseEntity.status(HttpStatus.SC_OK).body("SelectedCustomizationType deleted");
                }else{
                    return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("SelectedCustomizationType not found");
                }
            }
        }
    }

}
