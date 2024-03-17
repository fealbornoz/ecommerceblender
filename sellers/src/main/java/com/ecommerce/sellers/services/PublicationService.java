package com.ecommerce.sellers.services;

import org.apache.http.HttpStatus;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.sellers.dtos.PersonalizationDTO;
import com.ecommerce.sellers.dtos.PublicationDTO;
import com.ecommerce.sellers.models.FinalProduct;
import com.ecommerce.sellers.models.Personalization;
import com.ecommerce.sellers.models.Publication;
import com.ecommerce.sellers.models.SelectedCustomizationArea;
import com.ecommerce.sellers.models.Seller;
import com.ecommerce.sellers.models.Store;
import com.ecommerce.sellers.repositories.StoreRepository;

@Service
public class PublicationService {
    
     @Autowired
    private StoreRepository storeRepository;

    public @ResponseBody ResponseEntity<String> addPublication(Long idStore,@RequestBody PublicationDTO publicationDTO) {
        if(idStore == null){
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("idStore is required");
        }
        else{
            Store store = storeRepository.findById(idStore).get();
            FinalProduct finalProduct = createFinalProduct(publicationDTO, store.getSeller());
            Publication newPublication = new Publication(finalProduct);
            store.addPublication(newPublication);
            storeRepository.save(store);
            return ResponseEntity.status(HttpStatus.SC_CREATED).body("Publication created");
        }
    }


    private FinalProduct createFinalProduct(PublicationDTO publicationDTO, Seller seller) {
        FinalProduct finalProduct = new FinalProduct(publicationDTO.getFinalProduct(), seller);
        publicationDTO.getFinalProduct().getPersonalizations().forEach(personalization -> {
            Personalization newPersonalization = createPersonalization(personalization);
            finalProduct.addPersonalization(newPersonalization);
        });
        return finalProduct;
    }

    private Personalization createPersonalization(PersonalizationDTO personalizationDTO) {
        SelectedCustomizationArea selectedCustomizationArea = new SelectedCustomizationArea(personalizationDTO.getSelectedCustomizationArea());
        Personalization newPersonalization = new Personalization(selectedCustomizationArea);
        newPersonalization.getSelectedCustomizationType().forEach(customizationType -> {
            newPersonalization.addSelectedCustomizationType(customizationType);
        });
        return newPersonalization;
    }


}
