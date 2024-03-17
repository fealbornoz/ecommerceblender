package com.ecommerce.buyers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.buyers.dtos.BuyerDTO;
import com.ecommerce.buyers.models.Buyer;
import com.ecommerce.buyers.repositories.BuyerRepository;

@RepositoryRestController
@RequestMapping("/buyers")
public class BuyerController {

    @Autowired
    private BuyerRepository buyerRepository;

    @PostMapping("/")
    public @ResponseBody ResponseEntity<String> createBuyerUser(@RequestBody BuyerDTO buyer) {

        if (buyer.getName() == null || buyer.getLastName() == null) {
            return ResponseEntity.badRequest().body("name and last name are required");
        } else {
            Buyer newBuyer = new Buyer(buyer.getName(), buyer.getLastName());
            buyerRepository.save(newBuyer);
            return ResponseEntity.ok("Buyer created");
        }

    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<Object> getAllBuyers() {
        return ResponseEntity.ok(buyerRepository.findAll());
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> getBuyerById(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("id is required");
        } else if (!buyerRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Buyer not found");
        } else
            return ResponseEntity.ok(buyerRepository.findById(id));
    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateBuyer(@PathVariable("id") Long id, @RequestBody BuyerDTO buyer) {
        if (id == null) {
            return ResponseEntity.badRequest().body("id is required");
        } else if (!buyerRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Buyer not found");
        } else {
            Buyer buyerToUpdate = buyerRepository.findById(id).get();
            buyerToUpdate.setName(buyer.getName());
            buyerToUpdate.setLastName(buyer.getLastName());
            buyerRepository.save(buyerToUpdate);
            return ResponseEntity.ok("Buyer updated");
        }
    }



    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteBuyer(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("id is required");
        } else if (!buyerRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Buyer not found");
        } else {
            buyerRepository.deleteById(id);
            return ResponseEntity.ok("Buyer deleted");
        }
    }

}
