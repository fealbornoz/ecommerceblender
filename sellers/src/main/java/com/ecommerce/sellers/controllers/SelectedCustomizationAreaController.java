package com.ecommerce.sellers.controllers;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.sellers.dtos.SelectedCustomizationAreaDTO;
import com.ecommerce.sellers.models.SelectedCustomizationArea;
import com.ecommerce.sellers.repositories.SelectedCustomizationAreaRepository;

@RestController
@RequestMapping("/selectedCustomizationArea")
public class SelectedCustomizationAreaController {

    @Autowired
    private SelectedCustomizationAreaRepository selectedCustomizationAreaRepository;

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateSelectedCustomizationArea(@PathVariable("id") Long id,
            @RequestBody SelectedCustomizationAreaDTO selectedCustomizationAreaDTO) {

        if (id == null || selectedCustomizationAreaDTO.getName() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
                    .body("id and selectedCustomizationAreaDTO are required");
        } else {
            Optional<SelectedCustomizationArea> selectedCustomizationAreaToUpdate = selectedCustomizationAreaRepository
                    .findById(id);
            if (!selectedCustomizationAreaToUpdate.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("SelectedCustomizationArea not found");
            } else {
                SelectedCustomizationArea selectedCustomizationArea = selectedCustomizationAreaToUpdate.get();
                selectedCustomizationArea.setName(selectedCustomizationAreaDTO.getName());
                selectedCustomizationAreaRepository.save(selectedCustomizationArea);
                return ResponseEntity.status(HttpStatus.SC_OK).body("SelectedCustomizationArea updated");

            }
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteSelectedCustomizationArea(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id is required");
        } else {
            Optional<SelectedCustomizationArea> selectedCustomizationAreaToDelete = selectedCustomizationAreaRepository
                    .findById(id);
            if (!selectedCustomizationAreaToDelete.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("SelectedCustomizationArea not found");
            } else {
                SelectedCustomizationArea selectedCustomizationArea = selectedCustomizationAreaToDelete.get();
                if (selectedCustomizationArea != null) {

                    selectedCustomizationAreaRepository.delete(selectedCustomizationArea);
                    return ResponseEntity.status(HttpStatus.SC_OK).body("SelectedCustomizationArea deleted");
                } else {
                    return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("SelectedCustomizationArea not found");
                }
            }
        }
    }

}
