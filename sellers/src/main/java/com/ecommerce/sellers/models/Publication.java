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

    @Column(name = "sales_count")
    private Integer salesCount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "final_product_id", referencedColumnName = "id")
    private FinalProduct finalProduct;

    // Esto hace que tengamos bidireccionalidad
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @OneToOne(mappedBy = "publication")
    private Sales sale;

}
