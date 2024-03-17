package com.ecommerce.sellers.controllers;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.sellers.dtos.PaymentMethodDTO;
import com.ecommerce.sellers.models.PaymentMethod;
import com.ecommerce.sellers.repositories.PaymentMethodRepository;

@RepositoryRestController
@RequestMapping("/paymentMethods")
public class PaymentMethodsController {

    
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;



    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentMethodDTO paymentMethodDTO) {

        if (id == null || paymentMethodDTO == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id and paymentMethodDTO are required");
        } else {
            Optional<PaymentMethod> paymentMethodToUpdate = paymentMethodRepository.findById(id);
            if (paymentMethodToUpdate.isPresent()) {
                paymentMethodToUpdate.get().setName(paymentMethodDTO.getName());
                paymentMethodRepository.save(paymentMethodToUpdate.get());
                return ResponseEntity.status(HttpStatus.SC_OK).body("PaymentMethod updated");
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("PaymentMethod not found");
            }
        }
    }


    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deletePaymentMethod(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id is required");
        } else {
            Optional<PaymentMethod> paymentMethodToDelete = paymentMethodRepository.findById(id);
            if (paymentMethodToDelete.isPresent()) {
                paymentMethodRepository.delete(paymentMethodToDelete.get());
                return ResponseEntity.status(HttpStatus.SC_OK).body("PaymentMethod deleted");
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("PaymentMethod not found");
            }
        }
    }

   

}
