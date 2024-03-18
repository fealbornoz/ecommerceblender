package com.ecommerce.buyers.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Setter
@Getter
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "buyer_id", referencedColumnName = "id")
	private Buyer buyer;

	@OneToMany(mappedBy = "cart")
	private List<Item> items;

	public Cart(Buyer buyer) {
		this.buyer = buyer;
		buyer.setCart(this);
		this.items = new ArrayList<>();
	}

	public void addItem(Item item) {
		this.items.add(item);
		item.setCart(this);
	}

}
