package com.ecommerce.sellers.dtos;

import java.util.Set;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FinalProductDTO {
    
    Long productBaseId;
    String name;
    String description;
    Double price;
    Long manufacturingTime;
    Set<PersonalizationDTO> personalizations;
}
