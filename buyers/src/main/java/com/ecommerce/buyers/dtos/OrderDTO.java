package com.ecommerce.buyers.dtos;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {
    Integer store_id;
    String paymentMethod;
}
