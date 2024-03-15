package com.ecommerce.sellers.models;

import java.time.LocalDateTime;

import com.ecommerce.sellers.dtos.PublicationDTO;

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

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "date")
    private LocalDateTime date;

    @Enumerated
    @Column(name = "state")
    private PublicationState state;

    @Column(name = "sales_count")
    private Integer salesCount = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "final_product_id", referencedColumnName = "id")
    private FinalProduct finalProduct;

    // Esto hace que tengamos bidireccionalidad
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @OneToOne(mappedBy = "publication")
    private Sales sale;

    public Publication(PublicationDTO publication, FinalProduct product, Store store) {

        this.name = publication.getName();
        this.date = LocalDateTime.now();
        publication.getState();
        this.state = PublicationState.ACTIVATED;
        this.salesCount = this.salesCount + 1;
        this.finalProduct = product;
        this.store = store;

    }

}
