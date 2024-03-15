package com.ecommerce.sellers.dtos;

import java.util.List;
import java.util.Set;

import com.ecommerce.sellers.models.FinalProduct;
import com.ecommerce.sellers.models.SelectedCustomizationArea;
import com.ecommerce.sellers.models.SelectedCustomizationType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalizationDTO {


    String name;
    Boolean isActive;
    String possibleCustomization;
    Set<FinalProduct> finalProducts;
    List<SelectedCustomizationArea> selectedCustomizationArea;
    List<SelectedCustomizationType> selectedCustomizationType;
    
}
