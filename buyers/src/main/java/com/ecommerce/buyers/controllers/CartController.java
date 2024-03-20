package com.ecommerce.buyers.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.buyers.dtos.ItemDTO;
import com.ecommerce.buyers.models.*;
import com.ecommerce.buyers.repositories.BuyerRepository;
import com.ecommerce.buyers.repositories.CartRepository;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/createItemsInCart/{idBuyer}")
    public @ResponseBody ResponseEntity<String> addItemToCart(@PathVariable("idBuyer") Long idBuyer,
            @RequestBody ItemDTO itemDTO) {
        if (idBuyer == null || itemDTO.getNamePublication() == null || itemDTO.getPublicationId() == null
                || itemDTO.getQuantity() == null || itemDTO.getPrice() == null || itemDTO.getStoreId() == null) {
            return ResponseEntity.badRequest().body("idBuyer is required");
        } else {
            Cart cart;
            Optional<Cart> cartOptional = cartRepository.findByBuyerId(idBuyer);
            if (cartOptional.isPresent()) {
                cart = cartOptional.get();
            } else {
                Buyer buyer = buyerRepository.findById(idBuyer)
                        .orElseThrow(() -> new ResourceNotFoundException("Buyer not found"));
                cart = new Cart(buyer);
                cartRepository.save(cart);
            }
            Item itemIsExisting = cart.getItems().stream()
                    .filter(i -> i.getPublicationId().equals(itemDTO.getPublicationId()))
                    .findFirst().orElse(null);
            if (itemIsExisting != null) {
                itemIsExisting.setQuantity(itemIsExisting.getQuantity() + itemDTO.getQuantity());
                cartRepository.save(cart);
                return ResponseEntity.ok("Item added to cart");
            } else {
                Item newItem = new Item(itemDTO, cart);
                cart.addItem(newItem);
                cartRepository.save(cart);
                return ResponseEntity.ok("Item added to cart");
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
                Cart cartIsExisting = cart.get();
                if (cartIsExisting.getItems().isEmpty()) {
                    return ResponseEntity.ok("Cart is already empty");
                } else {
                    List<Item> items = cart.get().getItems();
                    items.clear();
                    cartRepository.save(cartIsExisting);
                    return ResponseEntity.ok("Cart cleared");

                }

            }
        }
    }

}
