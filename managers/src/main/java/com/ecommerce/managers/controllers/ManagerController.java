package com.ecommerce.managers.controllers;

import java.util.*;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.managers.dtos.ManagerDTO;
import com.ecommerce.managers.dtos.ProductBaseDTO;
import com.ecommerce.managers.models.Manager;
import com.ecommerce.managers.models.ProductBase;
import com.ecommerce.managers.repositories.ManagerRepository;
import com.ecommerce.managers.repositories.ProductBaseRepository;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

@RepositoryRestController
@RequestMapping("/managers")
public class ManagerController {

    @PersistenceContext
    private ManagerRepository managerRepository;

    @PostMapping("/")
    public @ResponseBody ResponseEntity<String> createManager(@RequestBody ManagerDTO manager) {

        if (manager.getName().isEmpty() || manager.getLastName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Name and last name are required.");
        } else {
            Manager newManager = new Manager(manager.getName(), manager.getLastName());

            managerRepository.save(newManager);

            return ResponseEntity.status(HttpStatus.SC_CREATED).body("Manager created, id: " + newManager.getId());
        }
    }

    @GetMapping("/")
    public @ResponseBody ResponseEntity<Object> getAllManagers() {

        if (managerRepository.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No managers found");
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(managerRepository.findAll());
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> getManagerById(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<Manager> manager = managerRepository.findById(id);

            if (manager.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Manager not found");
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(manager);
            }
        }

    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<String> updateManager(@PathVariable("id") Long id,
            @RequestBody ManagerDTO manager) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<Manager> existingManager = managerRepository.findById(id);

            if (existingManager.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Manager not found");
            } else {
                Manager managerToUpdate = existingManager.get();
                managerToUpdate.setName(manager.getName());
                managerToUpdate.setLastName(manager.getLastName());

                managerRepository.save(managerToUpdate);

                return ResponseEntity.status(HttpStatus.SC_OK).body("Manager updated");
            }
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteManager(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Id is required");
        } else {
            Optional<Manager> existingManager = managerRepository.findById(id);

            if (existingManager.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Manager not found");
            } else {

                managerRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.SC_OK).body("Manager deleted");
            }
        }
    }

}