package com.ecommerce.managers.dtos;

import com.ecommerce.managers.models.CustomizationArea;
import com.ecommerce.managers.models.CustomizationType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PossibleCustomizationDTO {

    CustomizationArea customizationArea;
    CustomizationType customizationType;
}
