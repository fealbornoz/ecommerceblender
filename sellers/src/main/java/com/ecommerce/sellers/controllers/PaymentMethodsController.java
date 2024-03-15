package com.ecommerce.sellers.controllers;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.sellers.dtos.PaymentMethodDTO;
import com.ecommerce.sellers.models.PaymentMethod;
import com.ecommerce.sellers.models.Seller;
import com.ecommerce.sellers.repositories.PaymentMethodRepository;
import com.ecommerce.sellers.repositories.SellerRepository;

@RepositoryRestController
@RequestMapping("/paymentMethods")
public class PaymentMethodsController {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @PostMapping("/{idSeller}")
    public @ResponseBody ResponseEntity<Object> createPaymentMethod(@PathVariable("idSeller") Long id,
            @RequestBody PaymentMethodDTO paymentMethod) {

        if (paymentMethod.getName() == null || id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("All fields are required.");
        } else {

            Seller sellerFound = sellerRepository.findById(id).get();
            PaymentMethod newPaymentMethod = new PaymentMethod(paymentMethod.getName(), sellerFound);
            paymentMethodRepository.save(newPaymentMethod);

            return ResponseEntity.status(HttpStatus.SC_CREATED).body(newPaymentMethod);
        }

    }

}
