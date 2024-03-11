package com.ecommerce.sellers.models;

import jakarta.persistence.*;

public class Personalization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "name", columnDefinition = "VARCHAR()")
    private String name;


    @JoinColumn(name = "isActive_possible_customization")
    private Boolean isActive;



    // Esto hace referencia a la tabla possible_customizations del microservicio managers
    @Column(name = "possible_customization")
    private String possibleCustomization;



    // Esto hace que tengamos bidireccionalidad
    @ManyToOne(optional = false)
    @JoinColumn(name = "final_product_id",  referencedColumnName = "id ")
    private FinalProduct finalProduct;
    
}
