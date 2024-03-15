package com.ecommerce.sellers.models;

import java.util.List;
import java.util.Set;

import com.ecommerce.sellers.dtos.FinalProductDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "final_product")
@Setter
@Getter
public class FinalProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR(50)")
    private String description;


    // Esto corresponde a la tabla de product_base, que se encuentra en el
    // microservicio de managers
    @Column(name = "price_base")
    private Double price;

    // Esto corresponde a la tabla de product_base, que se encuentra en el
    // microservicio de managers
    @Column(name = "product_base_id")
    private Long productBase;

    // Esto es bidireccionaldiad
    @OneToMany(mappedBy = "final_product")
    private List<Publication> publications;


    // Esto es bidireccionaldiad
    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    @ManyToMany
    private Set<Personalization> personalizations;


    public FinalProduct(FinalProductDTO finalProduct,Seller Seller) {
        this.name = finalProduct.getName();
        this.description = finalProduct.getDescription();
        this.price = finalProduct.getPrice();
        this.productBase = finalProduct.getProductBase();
        this.seller = Seller;
    }
    

}
