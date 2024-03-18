package com.ecommerce.buyers.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.buyers.dtos.OrderDTO;
import com.ecommerce.buyers.models.Buyer;
import com.ecommerce.buyers.models.Cart;
import com.ecommerce.buyers.models.Order;
import com.ecommerce.buyers.models.OrderItem;
import com.ecommerce.buyers.repositories.BuyerRepository;
import com.ecommerce.buyers.repositories.CartRepository;
import com.ecommerce.buyers.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private OrderRepository orderRepository;

    public String createOrder(Long idBuyer, Long idCart, OrderDTO orderDTO) {
        Cart cart = getCart(idCart);
        Buyer buyer = getBuyer(idBuyer);
        Order newOrder = new Order(orderDTO, buyer);
        addItemsToOrder(cart, newOrder);
        orderRepository.save(newOrder);
        return "Order created";
    }

    private Cart getCart(Long idCart) {
        if(idCart == null){
            throw new IllegalArgumentException("idCart is required");
        }else{
            return cartRepository.findById(idCart)
            .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        }
    }

    private Buyer getBuyer(Long idBuyer) {
        if(idBuyer == null){
            throw new IllegalArgumentException("idBuyer is required");
            
        }else{
            return buyerRepository.findById(idBuyer)
            .orElseThrow(() -> new IllegalArgumentException("Buyer not found"));
        }

    }

    private void addItemsToOrder(Cart cart, Order order) {
        cart.getItems().forEach(item -> {
            OrderItem newOrderItem = new OrderItem(item);
            order.addOrderItem(newOrderItem);
        });
    }


    
}
