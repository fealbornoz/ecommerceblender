package com.ecommerce.buyers.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.buyers.dtos.OrderDTO;
import com.ecommerce.buyers.models.Order;
import com.ecommerce.buyers.models.StateOrder;
import com.ecommerce.buyers.repositories.OrderRepository;
import com.ecommerce.buyers.service.OrderService;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

   /*  @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BuyerRepository buyerRepository; */

   /*  @PostMapping("/{idBuyer}/{idCart}")
    public @ResponseBody ResponseEntity<String> createOrder(@PathVariable("IdBuyer") Long idBuyer,

            @PathVariable("idCart") Long idCart, @RequestBody OrderDTO orderDTO) {
        if (idCart == null || idBuyer == null) {
            return ResponseEntity.badRequest().body("idCart is required");
        } else {
            Cart cart = cartRepository.findById(idCart).get();
            Buyer buyer = buyerRepository.findById(idBuyer).get();
            Order newOrder = new Order(orderDTO, buyer);

            for (int i = 0; i < cart.getItems().size(); i++) {
                Item item = cart.getItems().get(i);
                OrderItem orderItem = new OrderItem(item.getNamePublication(),
                        item.getQuantity(), item.getPrice(),
                        item.getPublicationId(), orderDTO.getStore_id());
                newOrder.addOrderItem(orderItem);

            }

            orderRepository.save(newOrder);

            return ResponseEntity.ok("Order created");
        }
    } */

    @PostMapping("/{idBuyer}/{idCart}")
    public @ResponseBody ResponseEntity<String> createOrder(@PathVariable("idBuyer") Long idBuyer,
            @PathVariable("idCart") Long idCart, @RequestBody OrderDTO orderDTO) {

        try {
            String message = orderService.createOrder(idBuyer, idCart, orderDTO);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> setStateOrder(@PathVariable("id") Long id,
            @RequestBody StateOrder stateOrder) {
        if (id == null) {
            return ResponseEntity.badRequest().body("id is required");
        } else {
            Optional<Order> order = orderRepository.findById(id);
            if(!order.isPresent()) {
                return ResponseEntity.badRequest().body("Order not found");
            }else{
                Order orderToUpdate = order.get();
                if (orderToUpdate.getStateOrder().equals(StateOrder.CANCELLED)) {
                    orderToUpdate.setStateOrder(StateOrder.CANCELLED);
                    return ResponseEntity.badRequest().body("Order is already cancelled");
                } else if (orderToUpdate.getStateOrder().equals(StateOrder.DISPATCHED)) {
                    orderToUpdate.setStateOrder(StateOrder.DISPATCHED);
                    return ResponseEntity.badRequest().body("Order is already dispatched");
                } else if (orderToUpdate.getStateOrder().equals(StateOrder.SENT)) {
                    orderToUpdate.setStateOrder(StateOrder.SENT);
                    return ResponseEntity.badRequest().body("Order is already sent");
                } else if (orderToUpdate.getStateOrder().equals(StateOrder.DELIVERED)) {
                    orderToUpdate.setStateOrder(StateOrder.DELIVERED);
                    return ResponseEntity.badRequest().body("Order is already delivered");
                }
                orderRepository.save(orderToUpdate);
                return ResponseEntity.ok("Order updated");
            }
          
        }
    }

}
