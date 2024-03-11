package com.ecommerce.buyers.models;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "buyer")
public class Buyer {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(15)")
    private String name;


    @Column(name = "last_name", columnDefinition = "VARCHAR(15)")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private Cart cart;


    @OneToMany(mappedBy = "buyer")
    private List<Order> orders;

    @OneToMany(mappedBy = "buyer")
    private List<ShippingAddress> shippingAddresses;

}
