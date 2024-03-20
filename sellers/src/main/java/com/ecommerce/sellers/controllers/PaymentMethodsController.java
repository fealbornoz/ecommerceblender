package com.ecommerce.sellers.controllers;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.sellers.dtos.PaymentMethodDTO;
import com.ecommerce.sellers.models.PaymentMethod;
import com.ecommerce.sellers.repositories.PaymentMethodRepository;

@RestController
@RequestMapping("/paymentMethods")
public class PaymentMethodsController {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updatePaymentMethod(@PathVariable("id") Long id,
            @RequestBody PaymentMethodDTO paymentMethodDTO) {

        if (id == null || paymentMethodDTO.getName() == null){
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id and paymentMethodDTO are required");
        } else {
            Optional<PaymentMethod> paymentMethodToUpdate = paymentMethodRepository.findById(id);
            if (!paymentMethodToUpdate.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("PaymentMethod not found");
            } else {
                PaymentMethod paymentMethod = paymentMethodToUpdate.get();
                paymentMethod.setName(paymentMethodDTO.getName());
                paymentMethodRepository.save(paymentMethod);
                return ResponseEntity.status(HttpStatus.SC_OK).body("PaymentMethod updated");
            }
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deletePaymentMethod(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id is required");
        } else {
            Optional<PaymentMethod> paymentMethodToDelete = paymentMethodRepository.findById(id);
            if (!paymentMethodToDelete.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("PaymentMethod not found");

            } else {
                PaymentMethod paymentMethod = paymentMethodToDelete.get();
                if (paymentMethod != null) {
                    paymentMethodRepository.delete(paymentMethod);
                    return ResponseEntity.status(HttpStatus.SC_OK).body("PaymentMethod deleted");
                } else {
                    return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to delete PaymentMethod");
                }
            }
        }
    }

}
