package com.ecommerce.sellers.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.sellers.dtos.PublicationDTO;
import com.ecommerce.sellers.models.Publication;
import com.ecommerce.sellers.models.Store;
import com.ecommerce.sellers.repositories.PublicationRepository;
import com.ecommerce.sellers.repositories.StoreRepository;

@RestController
@RequestMapping("/publication")

public class PublicationController {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/seller/{storeId}")
    public @ResponseBody ResponseEntity<Object> getPublicationsByStore(@PathVariable("storeId") Long storeId) {
        if (storeId == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Seller id is required");
        } else {
            Optional<Store> storeOptional = storeRepository.findById(storeId);
            if (!storeOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Seller not found");
            } else {
                List<Publication> publications = publicationRepository.findAllByStoreId(storeOptional.get());
                return ResponseEntity.status(HttpStatus.SC_OK).body(publications);

            }
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> getPublication(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<Publication> publication = publicationRepository.findById(id);
            if (!publication.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Publication not found");
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(publication.get());

            }
        }
    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> setPublication(@PathVariable("id") Long id,
            @RequestBody PublicationDTO publicationDTO) {

        if (id == null || publicationDTO.getState() == null || publicationDTO.getSalesCount() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id and name are required");
        } else {
            Optional<Publication> publicationToUpdate = publicationRepository.findById(id);

            if (!publicationToUpdate.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Publication not found");
            } else {
                Publication publicationUpdated = publicationToUpdate.get();
                publicationUpdated.setState(publicationDTO.getState());
                publicationUpdated.setSalesCount(publicationDTO.getSalesCount());
                publicationRepository.save(publicationUpdated);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Publication updated");
            }
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deletePublication(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<Publication> publicationToDelete = publicationRepository.findById(id);

            if (!publicationToDelete.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Publication not found");
            } else {
                Publication publication = publicationToDelete.get();
                if (publication != null) {
                    publicationRepository.delete(publication);
                    return ResponseEntity.status(HttpStatus.SC_OK).body("Publication deleted");
                }
                else{
                    return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to delete Publication");
                }
            }
        }
    }

}