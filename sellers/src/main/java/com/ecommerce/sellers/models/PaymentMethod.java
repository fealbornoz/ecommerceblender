package com.ecommerce.sellers.models;


import jakarta.persistence.*;


@Entity
@Table(name = "payment_method")
public class PaymentMethod {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name="name", columnDefinition = "VARCHAR(20)")
    private String name;




    //Esto es bidireccional
    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;



    public PaymentMethod(String name, Seller seller) {
        this.name = name;
        this.seller = seller;
    }


}
