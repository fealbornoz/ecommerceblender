package com.ecommerce.sellers.models;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "sales")
public class Sales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;


    @Column(name = "name", columnDefinition = "VARCHAR(15)")
    private String name;

    @OneToOne(mappedBy = "sales")
    private Seller seller;

    @OneToMany(mappedBy = "sales")
    private List<Publication> publications;





}
