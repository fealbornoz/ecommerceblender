package com.ecommerce.buyers.controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.buyers.models.Item;
import com.ecommerce.buyers.repositories.ItemRepository;

@RepositoryRestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @SuppressWarnings("null")
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