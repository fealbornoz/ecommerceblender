package com.ecommerce.buyers.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.buyers.dtos.ItemDTO;
import com.ecommerce.buyers.models.Buyer;
import com.ecommerce.buyers.models.Cart;
import com.ecommerce.buyers.models.Item;
import com.ecommerce.buyers.repositories.BuyerRepository;
import com.ecommerce.buyers.repositories.CartRepository;
import com.ecommerce.buyers.repositories.ItemRepository;

@RepositoryRestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @PostMapping("/{idCart}")
    public @ResponseBody ResponseEntity<String> createItem(@PathVariable("idCart") Long idCart,
            @RequestBody List<ItemDTO> item) {

        if (item.isEmpty() || idCart == null) {
            return ResponseEntity.badRequest().body("item is required");
        } else {
            Cart cart = cartRepository.findById(idCart).get();
            for (ItemDTO i : item) {
                if (i.getNamePublication() == null || i.getPrice() == null) {
                    return ResponseEntity.badRequest().body("name and price are required");
                } else {

                    Item newItem = new Item(i.getNamePublication(), i.getQuantity(), i.getPrice(), i.getPublicationId(),
                            i.getStoreId(), cart);
                    itemRepository.save(newItem);
                }
            }
            return ResponseEntity.ok("Item created");
        }
    }


    @DeleteMapping("/{idItem}")
    public @ResponseBody ResponseEntity<String> deleteItem(@PathVariable("idItem") Long idItem) {
        if (idItem == null) {
            return ResponseEntity.badRequest().body("idItem is required");
        } else {
            Optional<Item> item = itemRepository.findById(idItem);
            if (!item.isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
                itemRepository.delete(item.get());
                return ResponseEntity.ok("Item deleted");
            }
        }
    }




}
