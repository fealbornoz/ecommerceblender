package com.ecommerce.sellers.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalizationDTO {


    String name;
    String possibleCustomization;
    SelectedCustomizationAreaDTO selectedCustomizationArea;
    List<SelectedCustomizationTypeDTO> selectedCustomizationType;
    
}
