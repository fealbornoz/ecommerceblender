package com.ecommerce.sellers.controllers;

import java.util.Optional;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.sellers.dtos.PaymentMethodDTO;
import com.ecommerce.sellers.dtos.SellerDTO;
import com.ecommerce.sellers.dtos.StoreDTO;
import com.ecommerce.sellers.models.PaymentMethod;
import com.ecommerce.sellers.models.Seller;
import com.ecommerce.sellers.models.Store;
import com.ecommerce.sellers.repositories.PaymentMethodRepository;
import com.ecommerce.sellers.repositories.SellerRepository;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;


    @PostMapping("/")
    public @ResponseBody ResponseEntity<String> createSeller(@RequestBody SellerDTO sellerDTO) {

        if (sellerDTO.getName() == null || sellerDTO.getLastName() == null || sellerDTO.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("name and email are required");
        } else {

            Seller newSeller = new Seller(sellerDTO);

            sellerRepository.save(newSeller);

            return ResponseEntity.status(HttpStatus.SC_CREATED).body("Seller created");

        }
    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<Object> getSellers() {

        return ResponseEntity.status(HttpStatus.SC_OK).body(sellerRepository.findAll());
    }

    // Esto es para que el gestor, gestione a los vendedores y los pueda activar o
    // desactivar.
    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateSellerIsActive(@PathVariable Long id,
            @RequestBody Boolean isActive) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id and isActive are required");
        } else {
            Optional<Seller> sellerToUpdate = sellerRepository.findById(id);

            if (!sellerToUpdate.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Seller not found");
            } else {
                Seller sellerUpdated = sellerToUpdate.get();
                sellerUpdated.setIsActive(isActive);
                sellerRepository.save(sellerUpdated);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Seller updated");
            }
        }

    }

    @PostMapping("/addPaymentMethod/{id}")
    public @ResponseBody ResponseEntity<String> addPaymentMethod(@PathVariable("id") Long id,
            @RequestBody PaymentMethodDTO paymentMethodDTO) {

        if (id == null || paymentMethodDTO == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id and paymentMethods are required");
        } else {
            Optional<Seller> sellerToUpdate = sellerRepository.findById(id);

            if (!sellerToUpdate.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Seller not found");
            } else {
                Seller sellerUpdated = sellerToUpdate.get();
                PaymentMethod newPaymentMethod = new PaymentMethod(paymentMethodDTO);
                sellerUpdated.addPaymentMethod(newPaymentMethod);
                sellerRepository.save(sellerUpdated);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Payment method added");
            }
        }

    }

    @PostMapping("/createStore/{id}")
    public @ResponseBody ResponseEntity<String> createStore(@PathVariable("id") Long id,
            @RequestBody StoreDTO storeDTO) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("id is required");
        } else {
            Optional<Seller> sellerToUpdate = sellerRepository.findById(id);

            if (!sellerToUpdate.isPresent()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Seller not found");
            } else {
                Seller sellerUpdated = sellerToUpdate.get();
                Store newStore = new Store(storeDTO);
                sellerUpdated.createStore(newStore);
                sellerRepository.save(sellerUpdated);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Store created");
            }
        }

    }





}
