package com.ecommerce.sellers.models;

import java.util.HashSet;
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

    // Esto corresponde a la tabla de product_base, que se encuentra en el
    // microservicio de managers
    @Column(name = "product_base_id")
    private Long productBaseId;


    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR(50)")
    private String description;


    // Esto corresponde a la tabla de product_base, que se encuentra en el
    // microservicio de managers
    @Column(name = "price_base")
    private Double price;


    @Column(name = "manufacturingTime")
    private Long manufacturingTime;


    // Esto es bidireccionaldiad
    @OneToOne
    @JoinColumn(name = "publication_id", referencedColumnName = "id")
    private Publication publication;


    // Esto es bidireccionaldiad
    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    @OneToMany(mappedBy = "finalProduct")
    private Set<Personalization> personalizations;


    public FinalProduct(FinalProductDTO finalProduct,Seller Seller) {
        this.name = finalProduct.getName();
        this.productBaseId = finalProduct.getProductBaseId();
        this.description = finalProduct.getDescription();
        this.price = finalProduct.getPrice();
        this.manufacturingTime = finalProduct.getManufacturingTime();
        this.seller = Seller;
        this.personalizations = new HashSet<>();
    }


    public void addPersonalization(Personalization personalization) {
        this.personalizations.add(personalization);
        personalization.setFinalProduct(this);
    }
    

}
