package com.ecommerce.sellers.controllers;

import org.apache.catalina.Manager;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.sellers.dtos.FinalProductDTO;
import com.ecommerce.sellers.models.FinalProduct;
import com.ecommerce.sellers.models.Personalization;
import com.ecommerce.sellers.models.Seller;
import com.ecommerce.sellers.repositories.FinalProductRepository;
import com.ecommerce.sellers.repositories.PersonalizationRepository;
import com.ecommerce.sellers.repositories.SellerRepository;

@RepositoryRestController
@RequestMapping("/publication")
public class FinalProductController {

    @Autowired
    private FinalProductRepository finalProductRepository;

    @Autowired
    private PersonalizationRepository personalizationRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @PostMapping("/{idSeller}")
    public @ResponseBody ResponseEntity<String> createFinalProduct(@PathVariable("idSeller") Long idSeller,@RequestBody FinalProductDTO finalProduct) {



        if (idSeller == null || finalProduct.getName() == null || finalProduct.getDescription() == null
                || finalProduct.getPrice() == null || finalProduct.getManufacturingTime() == null
                || finalProduct.getProductBase() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("idProduct and idPersonalization are required");
        } else {


            Seller sellerFound = sellerRepository.findById(idSeller).get();
            FinalProduct newFinalProduct = new FinalProduct(finalProduct, sellerFound);

            for (Personalization personalization : finalProduct.getPersonalizations()) {

                if (personalization.getId() != null) {
                    Personalization newPersonalization = personalizationRepository.findById(personalization.getId()).get();
                    newFinalProduct.getPersonalizations().add(newPersonalization);
                    newPersonalization.getFinalProducts().add(newFinalProduct); 
                }
            }

            finalProductRepository.save(newFinalProduct);

            return ResponseEntity.status(HttpStatus.SC_CREATED).body("FinalProduct created");

        }

    }





}
