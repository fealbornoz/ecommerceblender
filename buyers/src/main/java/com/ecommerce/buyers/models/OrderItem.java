package com.ecommerce.buyers.models;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Setter
@Getter
public class OrderItem {

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

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	@JsonBackReference
	private Order order;

	public OrderItem(Item item) {
		this.namePublication = item.getNamePublication();
		this.quantity = item.getQuantity();
		this.price = item.getPrice();
		this.publicationId = item.getPublicationId();
		this.storeId = item.getStoreId();
	}


}
