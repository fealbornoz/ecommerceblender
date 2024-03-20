package com.ecommerce.sellers.controllers;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.sellers.dtos.FinalProductDTO;
import com.ecommerce.sellers.models.FinalProduct;
import com.ecommerce.sellers.repositories.FinalProductRepository;

@RestController
@RequestMapping("/finalProduct")
public class FinalProductController {

    @Autowired
    private FinalProductRepository finalProductRepository;

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateFinalProduct(@PathVariable("id") Long id,
            @RequestBody FinalProductDTO finalProductDTO) {

        if (id == null || finalProductDTO.getName() == null || finalProductDTO.getDescription() == null
                || finalProductDTO.getPrice() == null || finalProductDTO.getManufacturingTime() == null
                || finalProductDTO.getProductBaseId() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id and finalProductDTO are required");
        } else {
            Optional<FinalProduct> finalProductToUpdate = finalProductRepository.findById(id);
            if (!finalProductToUpdate.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("FinalProduct not found");
            } else {
                FinalProduct finalProduct = finalProductToUpdate.get();
                finalProduct.setName(finalProductDTO.getName());
                finalProduct.setDescription(finalProductDTO.getDescription());
                finalProduct.setPrice(finalProductDTO.getPrice());
                finalProduct.setManufacturingTime(finalProductDTO.getManufacturingTime());
                finalProduct.setProductBaseId(finalProductDTO.getProductBaseId());
                finalProductRepository.save(finalProduct);
                return ResponseEntity.status(HttpStatus.SC_OK).body("FinalProduct updated");
               
            }
        }
    }

}
