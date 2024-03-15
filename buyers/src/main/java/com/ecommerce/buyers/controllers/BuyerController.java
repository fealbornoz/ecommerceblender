package com.ecommerce.buyers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody ResponseEntity<String> createBuyerUser(@RequestBody BuyerDTO buyer){

        if(buyer.getName() == null || buyer.getLastName() == null){
            return ResponseEntity.badRequest().body("name and last name are required");
        }else{
            Buyer newBuyer = new Buyer(buyer.getName(), buyer.getLastName());
            buyerRepository.save(newBuyer);
            return ResponseEntity.ok("Buyer created");
        }

    }


    



    



    
}
