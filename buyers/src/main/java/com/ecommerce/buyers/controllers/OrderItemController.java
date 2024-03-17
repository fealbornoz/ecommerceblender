package com.ecommerce.buyers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.buyers.dtos.OrderItemDTO;
import com.ecommerce.buyers.models.OrderItem;
import com.ecommerce.buyers.repositories.OrderItemRepository;

@RepositoryRestController
public class OrderItemController {


    @Autowired
    private OrderItemRepository orderItemRepository;

    @PatchMapping("/orderItems/{id}")
    public @ResponseBody ResponseEntity<String> setOrderItem(@PathVariable("id") Long id, OrderItemDTO orderItemDTO) {

        if (id == null) {
            return ResponseEntity.badRequest().body("id is required");
        } else {
            OrderItem orderItem = orderItemRepository.findById(id).get();
            orderItem.setNamePublication(orderItemDTO.getNamePublication());
            orderItem.setPrice(orderItemDTO.getPrice());
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setPublicationId(orderItemDTO.getPublicationId());
            orderItem.setStoreId(orderItemDTO.getStoreId());
            orderItemRepository.save(orderItem);
            return ResponseEntity.ok("OrderItem updated");
        }

    }


    @DeleteMapping("/orderItems/{id}")
    public @ResponseBody ResponseEntity<String> deleteOrderItem(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("id is required");
        } else {
            orderItemRepository.deleteById(id);
            return ResponseEntity.ok("OrderItem deleted");
        }
    }




}
