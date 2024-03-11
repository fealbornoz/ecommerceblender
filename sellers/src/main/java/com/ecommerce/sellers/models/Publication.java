package com.ecommerce.sellers.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
@Table(name = "publication")
public class Publication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;


    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "date")
    private LocalDateTime date;
    
    @Column(name = "price")
    private Double price;

    @Enumerated
    @Column(name = "state")
    private PublicationState state;

    @Column(name = "isActive")
    private Boolean isActive;


    @ManyToOne(optional = false )
    @JoinColumn(name = "product_base_id", referencedColumnName = "id")
    private FinalProduct finalProduct;


    // Esto hace que tengamos bidireccionalidad
    @ManyToOne(optional = false )
    @JoinColumn(name = "tienda_id", referencedColumnName = "id")
    private Store store;


    
}
