package com.ecommerce.managers.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.managers.dtos.PossibleCustomizationDTO;
import com.ecommerce.managers.dtos.ProductBaseDTO;
import com.ecommerce.managers.models.CustomizationType;
import com.ecommerce.managers.models.Manager;
import com.ecommerce.managers.models.PossibleCustomization;
import com.ecommerce.managers.models.ProductBase;
import com.ecommerce.managers.repositories.ManagerRepository;
import com.ecommerce.managers.repositories.PossibleCustomizationRepository;
import com.ecommerce.managers.repositories.ProductBaseRepository;

@Service
public class ProductService {

    @Autowired
    private ProductBaseRepository productBaseRepository;

    @Autowired
    private PossibleCustomizationRepository possibleCustomizationRepository;

    @Autowired
    private ManagerRepository managerRepository;

    public List<PossibleCustomization> createPossibleCustomizations(List<PossibleCustomizationDTO> customizationDTOs) {
        return customizationDTOs.stream()
                .map(customizationDTO -> {
                    PossibleCustomization possibleCustomization = new PossibleCustomization(
                            customizationDTO.getCustomizationArea());

                    customizationDTO.getCustomizationTypes().stream()
                            .forEach(customizationTypeDTO -> {
                                CustomizationType customizationType = new CustomizationType(
                                        customizationTypeDTO.getName());
                                possibleCustomization.addCustomizationType(customizationType);
                            });

                    return possibleCustomizationRepository.save(possibleCustomization);
                })
                .collect(Collectors.toList());
    }

    public ProductBase createProductBase(ProductBaseDTO productBaseDTO,
            List<PossibleCustomization> possibleCustomizations) {
        ProductBase newProductBase = new ProductBase(productBaseDTO.getName(), productBaseDTO.getDescription(),
                productBaseDTO.getPrice(), productBaseDTO.getManufacturingTime());

        possibleCustomizations.stream().forEach(possibleCustomization -> {
            newProductBase.addPossibleCustomization(possibleCustomization);
        });

        return productBaseRepository.save(newProductBase);
    }

    public ProductBase createProductBaseAndCustomizations(Long managerId, ProductBaseDTO productBaseDTO) {
        List<PossibleCustomization> possibleCustomizations = createPossibleCustomizations(
                productBaseDTO.getPossibleCustomizations());
        ProductBase newProductBase = createProductBase(productBaseDTO, possibleCustomizations);

        Manager managerFound = null;
        if (managerId != null) {
            managerFound = managerRepository.findById(managerId).get();
        }

        if (managerFound != null) {
            managerFound.addProductBase(newProductBase);
            managerRepository.save(managerFound);
        }

        return newProductBase;
    }
}
