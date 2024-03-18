package com.ecommerce.sellers.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "publication")
@Setter
@Getter
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

  /*   @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name; */

    @Column(name = "date")
    private LocalDateTime date;

    @Enumerated
    @Column(name = "state")
    private PublicationState state;

    @Column(name = "sales_count")
    private Integer salesCount = 0;

    @OneToOne(mappedBy = "publication")
    private FinalProduct finalProduct;

    // Esto hace que tengamos bidireccionalidad
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

   @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    public Publication(FinalProduct finalProduct) {

        this.date = LocalDateTime.now();
        this.state = PublicationState.ACTIVATED;
        this.salesCount = this.salesCount + 1;
        this.finalProduct = finalProduct;
        finalProduct.setPublication(this);
    }


}
