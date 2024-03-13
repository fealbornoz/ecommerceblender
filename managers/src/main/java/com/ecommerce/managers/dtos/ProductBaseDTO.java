package com.ecommerce.managers.dtos;

import java.util.Set;

import com.ecommerce.managers.models.Manager;
import com.ecommerce.managers.models.PossibleCustomization;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductBaseDTO {
    String name;
    String description;
    Double price;
    Integer manufacturingTime;
    Manager manager;
    Set<PossibleCustomization> possibleCustomizations;
}
