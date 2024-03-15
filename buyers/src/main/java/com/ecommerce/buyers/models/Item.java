package com.ecommerce.buyers.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item")
@Setter
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "namePublication", columnDefinition = "VARCHAR(50)")
    private String namePublication;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "publication_id")
    private Integer publicationId;

    @Column(name = "store_id")
    private Integer storeId;

    // Hay bidireccionalidad
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    public Item(String namePublication, Integer quantity, Double price, Integer publicationId, Integer storeId,
            Cart cart) {
        this.namePublication = namePublication;
        this.quantity = quantity;
        this.price = price;
        this.publicationId = publicationId;
        this.storeId = storeId;
        this.cart = cart;
    }

}
