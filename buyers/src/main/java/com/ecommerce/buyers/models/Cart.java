package com.ecommerce.buyers.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;


    @OneToOne(mappedBy = "cart")
	private Buyer buyer;


    @OneToMany(mappedBy = "cart")
	private List<Item> items;

    @Column(name = "store_id")
	private Integer storeId;
    
}
