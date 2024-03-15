package com.ecommerce.buyers.dtos;

import com.ecommerce.buyers.models.Cart;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDTO {

    Integer quantity;
    Double price;
    Integer publicationId;
    Integer storeId;
    Cart cart;
    String namePublication;

    
}
