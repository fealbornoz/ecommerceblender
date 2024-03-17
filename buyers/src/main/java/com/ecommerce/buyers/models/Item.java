package com.ecommerce.buyers.models;

import com.ecommerce.buyers.dtos.OrderItemDTO;

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

    public Item(OrderItemDTO orderItemDTO,
            Cart cart) {
        this.namePublication = orderItemDTO.getNamePublication();
        this.quantity = orderItemDTO.getQuantity();
        this.price = orderItemDTO.getPrice();
        this.publicationId = orderItemDTO.getPublicationId();
        this.storeId = orderItemDTO.getStoreId();
        this.cart = cart;
    }

}
