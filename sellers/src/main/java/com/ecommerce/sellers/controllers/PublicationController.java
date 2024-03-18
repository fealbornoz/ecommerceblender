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
import com.ecommerce.sellers.models.Seller;
import com.ecommerce.sellers.repositories.PublicationRepository;
import com.ecommerce.sellers.repositories.SellerRepository;
import com.netflix.discovery.converters.Auto;

@RestController
@RequestMapping("/publication")

public class PublicationController {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @GetMapping("/seller/{sellerId}")
    public @ResponseBody ResponseEntity<Object> getPublicationsBySeller(@PathVariable("sellerId") Long sellerId) {
        if (sellerId == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Seller id is required");
        } else {
            Optional<Seller> seller = sellerRepository.findById(sellerId);
            if (!seller.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Seller not found");
            } else {
                List<Publication> publications = publicationRepository.findBySeller(seller.get());
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
            if (publication.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_OK).body(publication.get());
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Publication not found");
            }
        }
    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> setPublication(@PathVariable("id") Long id,
            @RequestBody PublicationDTO publicationDTO) {

        if (id == null || publicationDTO == null) {
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
                publicationRepository.delete(publicationToDelete.get());
                return ResponseEntity.status(HttpStatus.SC_OK).body("Publication deleted");
            }
        }
    }

}