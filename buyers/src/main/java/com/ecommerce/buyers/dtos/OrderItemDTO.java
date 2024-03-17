package com.ecommerce.buyers.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemDTO {
    String namePublication;
    Integer quantity;
    Double price;
    Integer publicationId;
    Integer storeId;
    
}
