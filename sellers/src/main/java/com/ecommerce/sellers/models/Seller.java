package com.ecommerce.sellers.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ecommerce.sellers.dtos.SellerDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seller")
@Setter
@Getter
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(name = "name", columnDefinition = "VARCHAR(15)")
    private String name;

    @Column(name = "lastName", columnDefinition = "VARCHAR(15)")
    private String lastName;

    @Column(name = "isActive", columnDefinition = "BOOLEAN")
    private Boolean isActive;

    @OneToMany(mappedBy = "seller")
    private Set<PaymentMethod> paymentMethods;


    //Por ahora hago que tenga sólo una tienda, pero en el futuro puede tener más
    @OneToOne(mappedBy = "seller")
    private Store store;

    



    public Seller(SellerDTO sellerDTO) {
        this.name = sellerDTO.getName();
        this.lastName = sellerDTO.getLastName();
        this.isActive = true;
        this.paymentMethods = new HashSet<>();
    }


    public void addPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethods.add(paymentMethod);
        paymentMethod.setSeller(this);
    }

    public void createStore(Store store){
        this.store = store;
        store.setSeller(this);
    }





}
