package com.ecommerce.managers.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @OneToOne
    @JoinColumn(name = "possible_customizations_id", referencedColumnName = "id")
    private PossibleCustomization possibleCustomizations;


}
