package com.ecommerce.sellers.models;

import jakarta.persistence.*;

public class Personalization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "name")
    private String name;


    @JoinColumn(name = "isActive_possible_customization")
    private Boolean isActive;







    // Esto hace referencia a la tabla possible_customizations del microservicio managers
    @Column(name = "possible_customizations")
    private String possibleCustomization;




    // Esto hace que tengamos bidireccionalidad
    @OneToOne(optional = false)
    @JoinColumn(name = "producto_final_id",  referencedColumnName = "id ")
    private FinalProduct finalProduct;
    
}
