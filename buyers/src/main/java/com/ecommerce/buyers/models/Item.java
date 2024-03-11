package com.ecommerce.buyers.models;


import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "publication_id")
    private Integer publicationId;

    @Column(name = "price")
    private Double price;



    // Hay bidireccionalidad
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

}
