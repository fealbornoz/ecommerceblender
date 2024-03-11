package com.ecommerce.sellers.models;


import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name = "final_product")
public class FinalProduct {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;



    @OneToOne(mappedBy = "final_product")
    private Personalization personalizations;


    // Esto corresponde a la tabla de product_base, que se encuentra en el microservicio de managers
    @Column(name = "product_base_id")
    private Integer productBase;

    // Esto corresponde a la tabla de product_base, que se encuentra en el microservicio de managers
    @Column(name = "price_base")
    private Double price;

    // Esto es bidireccionaldiad 
    @OneToMany(mappedBy = "final_product")
    private List<Publication> publications;

    // Esto es bidireccionaldiad
    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

}
