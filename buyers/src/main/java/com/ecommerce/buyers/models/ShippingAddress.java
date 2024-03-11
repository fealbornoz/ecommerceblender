package com.ecommerce.buyers.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "shipping_address")
public class ShippingAddress {

    @Id
	@GeneratedValue
	private Integer id;


    @Column(name = "street", columnDefinition = "VARCHAR(15)")
    private String street;
    @Column(name = "city", columnDefinition = "VARCHAR(15)")
    private String city;
    @Column(name = "state", columnDefinition = "VARCHAR(15)")
    private String state;
    @Column(name = "country", columnDefinition = "VARCHAR(15)")
    private String country;
    @Column(name = "zip_code", columnDefinition = "VARCHAR(15)")    
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private Buyer buyer;



}
