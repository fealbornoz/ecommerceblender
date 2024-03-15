package com.ecommerce.sellers.controllers;


import java.util.Optional;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.sellers.dtos.SellerDTO;
import com.ecommerce.sellers.models.Seller;
import com.ecommerce.sellers.repositories.SellerRepository;

@RepositoryRestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;

    @PostMapping("/")
    public @ResponseBody ResponseEntity<String> createSeller(@RequestBody SellerDTO seller) {

        if (seller.getName() == null || seller.getLastName() == null || seller.getEmail() == null
                || seller.getIsActive() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("name and email are required");
        } else {

            Seller newSeller = new Seller(seller.getName(), seller.getLastName(), seller.getEmail());

            sellerRepository.save(newSeller);

            return ResponseEntity.status(HttpStatus.SC_CREATED).body("Seller created");

        }
    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<Object> getSellers() {

        return ResponseEntity.status(HttpStatus.SC_OK).body(sellerRepository.findAll());
    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateSellerIsActive(@PathVariable Long id,
            @RequestBody SellerDTO seller) {

        if (id == null || seller.getIsActive() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id and isActive are required");
        } else {
            Optional<Seller> sellerToUpdate = sellerRepository.findById(id);

            if (!sellerToUpdate.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Seller not found");
            } else {
                Seller sellerUpdated = sellerToUpdate.get();
                sellerUpdated.setIsActive(seller.getIsActive());
                sellerRepository.save(sellerUpdated);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Seller updated");
            }
        }

    }


}
