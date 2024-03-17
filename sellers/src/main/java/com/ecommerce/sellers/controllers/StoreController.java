package com.ecommerce.sellers.controllers;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.sellers.dtos.PublicationDTO;
import com.ecommerce.sellers.dtos.StoreDTO;
import com.ecommerce.sellers.models.Store;
import com.ecommerce.sellers.repositories.StoreRepository;
import com.ecommerce.sellers.services.PublicationService;

@RepositoryRestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;


    @Autowired
    private PublicationService publicationService;
    
    @GetMapping("/")
    public @ResponseBody ResponseEntity<Object> getStores() {

        return ResponseEntity.status(HttpStatus.SC_OK).body(storeRepository.findAll());
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> getStore(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<Store> store = storeRepository.findById(id);
            if (store.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_OK).body(store.get());
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Store not found");
            }
        }
    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateStore(@PathVariable Long id, @RequestBody StoreDTO store) {

        if (id == null || store.getName() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id and name are required");
        } else {
            Optional<Store> storeToUpdate = storeRepository.findById(id);

            if (!storeToUpdate.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Store not found");
            } else {
                Store storeUpdated = storeToUpdate.get();
                storeUpdated.setName(store.getName());
                storeRepository.save(storeUpdated);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Store updated");
            }
        }

    }

    @PostMapping("/addPublication/{idStore}")
    public @ResponseBody ResponseEntity<String> addPublication(@PathVariable("idStore") Long idStore,
            @RequestBody PublicationDTO publicationDTO) {

        if (idStore == null || publicationDTO == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("idStore and publicationDTO are required");
        } else {
            try {
                String message = publicationService.addPublication(idStore, publicationDTO);
                return ResponseEntity.status(HttpStatus.SC_OK).body(message);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(e.getMessage());
            }
        }
    }
}
