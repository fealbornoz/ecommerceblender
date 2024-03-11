package com.ecommerce.buyers.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

public class OrderItem {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;


    @Column(name = "name_publication", columnDefinition = "VARCHAR(50)")
	private String namePublication;

    @Column(name = "quantity")
	private Integer quantity;

    @ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	@JsonBackReference
	private Order order;







}
