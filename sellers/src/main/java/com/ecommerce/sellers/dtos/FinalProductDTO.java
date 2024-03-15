package com.ecommerce.sellers.dtos;

import java.util.Set;

import com.ecommerce.sellers.models.Personalization;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FinalProductDTO {
    
    String name;
    String description;
    Double price;
    Integer manufacturingTime;
    Long productBase;
    Set<Personalization> personalizations;
}
