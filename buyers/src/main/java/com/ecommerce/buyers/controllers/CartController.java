package com.ecommerce.buyers.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ecommerce.buyers.models.*;
import com.ecommerce.buyers.repositories.BuyerRepository;
import com.ecommerce.buyers.repositories.CartRepository;

@RepositoryRestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/{idBuyer}")
    public @ResponseBody ResponseEntity<String> createCart(@PathVariable("idBuyer") Long idBuyer) {

        if (idBuyer == null) {
            return ResponseEntity.badRequest().body("idBuyer is required");
        } else {
            Optional<Buyer> buyer = buyerRepository.findById(idBuyer);
            if (!buyer.isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
                Optional<Cart> cartIsFound = cartRepository.findByBuyerId(idBuyer);

                if (cartIsFound.isPresent()) {
                    return ResponseEntity.badRequest().body("Cart already exists");
                } else {
                    Cart newCart = new Cart(buyer.get());
                    cartRepository.save(newCart);
                    return ResponseEntity.ok("Cart created");
                }
            }
        }

    }

    @DeleteMapping("/{idCart}/items")
    public @ResponseBody ResponseEntity<String> clearCart(@PathVariable("idCart") Long idCart) {
        if (idCart == null) {
            return ResponseEntity.badRequest().body("idCart is required");
        } else {
            Optional<Cart> cart = cartRepository.findById(idCart);
            if (!cart.isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
                List<Item> items = cart.get().getItems();
                items.clear();
                cartRepository.save(cart.get());
                return ResponseEntity.ok("Cart cleared");
            }
        }
    }

    


}
