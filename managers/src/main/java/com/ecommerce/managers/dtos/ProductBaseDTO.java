package com.ecommerce.managers.dtos;


import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductBaseDTO {
    String name;
    String description;
    Double price;
    Integer manufacturingTime;
    List<PossibleCustomizationDTO> possibleCustomizations;
}
