package com.ecommerce.buyers.dtos;

import java.util.List;

import com.ecommerce.buyers.models.Buyer;
import com.ecommerce.buyers.models.StateOrder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {
    Buyer buyer;
    Integer store_id;
    String paymentMethod;
    StateOrder stateOrder;
    List<OrderItemDTO> orderItem;

}
