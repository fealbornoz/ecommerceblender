package com.ecommerce.sellers.dtos;


import com.ecommerce.sellers.models.PublicationState;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PublicationDTO {
    PublicationState state;
    Integer salesCount;
    FinalProductDTO finalProduct;

}
