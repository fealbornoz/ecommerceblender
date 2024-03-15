package com.ecommerce.sellers.controllers;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.sellers.dtos.PublicationDTO;
import com.ecommerce.sellers.models.FinalProduct;
import com.ecommerce.sellers.models.Publication;
import com.ecommerce.sellers.models.Store;
import com.ecommerce.sellers.repositories.FinalProductRepository;
import com.ecommerce.sellers.repositories.PublicationRepository;
import com.ecommerce.sellers.repositories.StoreRepository;

@RepositoryRestController
@RequestMapping("/publication")

public class PublicationController {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private FinalProductRepository finalProductRepository;

    @Autowired
    private StoreRepository storeRepository;

    @PostMapping("/{idProduct}/{idStore}")
    public ResponseEntity<String> createPublication(@PathVariable("idProduct") Long idProduct,
            @PathVariable("idStore") Long idStore, PublicationDTO publication) {

        if (idProduct == null || idStore == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("idProduct and idStore are required");
        } else {

            FinalProduct productFound = finalProductRepository.findById(idProduct).get();
            Store storeFound = storeRepository.findById(idStore).get();

            Publication newPublication = new Publication(publication, productFound, storeFound);

            publicationRepository.save(newPublication);

            return ResponseEntity.status(HttpStatus.SC_CREATED).body("Publication created");

        }

    }


    
}