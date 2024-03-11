package com.ecommerce.sellers.models;

import jakarta.persistence.*;

@Entity
@Table(name = "store")
public class Store {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    

}
