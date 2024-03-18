package com.ecommerce.buyers.models;

import com.ecommerce.buyers.dtos.ItemDTO;

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

    public Item(ItemDTO itemDTO,
            Cart cart) {
        this.namePublication = itemDTO.getNamePublication();
        this.quantity = itemDTO.getQuantity();
        this.price = itemDTO.getPrice();
        this.publicationId = itemDTO.getPublicationId();
        this.storeId = itemDTO.getStoreId();
        this.cart = cart;
    }

}
