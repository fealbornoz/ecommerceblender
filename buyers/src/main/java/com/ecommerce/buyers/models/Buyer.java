package com.ecommerce.buyers.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "buyer")
@Setter
@Getter
public class Buyer {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(15)")
    private String name;


    @Column(name = "last_name", columnDefinition = "VARCHAR(15)")
    private String lastName;

    @OneToOne(mappedBy = "buyer")
	private Cart cart;


    @OneToMany(mappedBy = "buyer")
    private List<Order> orders;

    @OneToMany(mappedBy = "buyer")
    private List<ShippingAddress> shippingAddresses;


    public Buyer(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }


}
