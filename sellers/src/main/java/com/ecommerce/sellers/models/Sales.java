package com.ecommerce.sellers.models;



import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(15)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;


    public Sales(Seller seller, Publication publication) {
        this.seller = seller;
        this.publication = publication;
    }

}
