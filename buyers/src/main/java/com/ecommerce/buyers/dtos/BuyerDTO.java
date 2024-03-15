package com.ecommerce.buyers.dtos;

import com.ecommerce.buyers.models.Cart;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BuyerDTO {
    
    String name;
    String lastName;
    Cart cart;

}
