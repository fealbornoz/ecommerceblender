package com.ecommerce.buyers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.buyers.dtos.OrderDTO;
import com.ecommerce.buyers.models.Buyer;
import com.ecommerce.buyers.models.Cart;
import com.ecommerce.buyers.models.Item;
import com.ecommerce.buyers.models.Order;
import com.ecommerce.buyers.models.OrderItem;
import com.ecommerce.buyers.repositories.BuyerRepository;
import com.ecommerce.buyers.repositories.CartRepository;
import com.ecommerce.buyers.repositories.OrderRepository;

@RepositoryRestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @PostMapping("/{idBuyer}/{idCart}")
    public @ResponseBody ResponseEntity<String> createOrder(@PathVariable("IdBuyer") Long idBuyer,
            @PathVariable("idCart") Long idCart, @RequestBody OrderDTO order) {
        if (idCart == null || idBuyer == null) {
            return ResponseEntity.badRequest().body("idCart is required");
        } else {
            Cart cart = cartRepository.findById(idCart).get();
            Buyer buyer = buyerRepository.findById(idBuyer).get();
            Order newOrder = new Order(buyer, order.getStore_id(), order.getPaymentMethod());

            for (int i = 0; i < cart.getItems().size(); i++) {
                Item item = cart.getItems().get(i);
                OrderItem orderItem = new OrderItem(item.getNamePublication(), item.getQuantity(), item.getPrice(),
                        item.getPublicationId(), order.getStore_id());
                newOrder.addOrderItem(orderItem);

            }

            orderRepository.save(newOrder);

            return ResponseEntity.ok("Order created");
        }
    }

}
