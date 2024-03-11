package com.ecommerce.sellers.models;

import java.util.Set;

import jakarta.persistence.*;



public class PaymentMethods {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name="name")
    private String name;




    //Esto es bidireccional
    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;





}
