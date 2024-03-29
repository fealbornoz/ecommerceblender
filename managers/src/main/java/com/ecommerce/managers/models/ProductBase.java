package com.ecommerce.managers.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.ecommerce.managers.dtos.ProductBaseDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_base")
@Setter
@Getter
public class ProductBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR(255)")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "manufacturingTime")
    private Integer manufacturingTime;

    @Column(name = "isActive")
    private Boolean isActive;

    @OneToMany(mappedBy = "productBase")
    private Set<PossibleCustomization> possibleCustomizations;

    // Acá hay bidireccionalidad
    @ManyToOne(optional = false)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    public ProductBase(String name, String description, Double price, Integer manufacturingTime) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.manufacturingTime = manufacturingTime;
        this.isActive = true;
        this.possibleCustomizations = new HashSet<>();
    }

    public void update(ProductBaseDTO productBaseDTO) {
        this.name = productBaseDTO.getName();
        this.description = productBaseDTO.getDescription();
        this.price = productBaseDTO.getPrice();
        this.manufacturingTime = productBaseDTO.getManufacturingTime();
    }


    public void addPossibleCustomization(PossibleCustomization possibleCustomization) {
        this.possibleCustomizations.add(possibleCustomization);
        possibleCustomization.setProductBase(this);
    }

}
