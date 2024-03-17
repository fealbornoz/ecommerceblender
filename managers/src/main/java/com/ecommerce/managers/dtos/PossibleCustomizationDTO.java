package com.ecommerce.managers.dtos;

import java.util.List;

import com.ecommerce.managers.models.CustomizationArea;
import com.ecommerce.managers.models.CustomizationType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PossibleCustomizationDTO {

    CustomizationArea customizationArea;
    List<CustomizationTypeDTO> customizationTypes;
}
