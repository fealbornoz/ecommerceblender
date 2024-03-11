package com.ecommerce.sellers.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "store")
public class Store {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(name = "name", columnDefinition = "VARCHAR(30)")
    private String name;

    
    @OneToMany(mappedBy = "store")
    private List<Publication> publications;



    @OneToOne(mappedBy = "store")
    private Seller seller;

}
