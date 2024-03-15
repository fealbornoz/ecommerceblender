package com.ecommerce.sellers.models;

import java.util.List;
import java.util.Set;


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

    @Column(name = "email", columnDefinition = "VARCHAR(20)")
    private String email;

    @Column(name = "isActive", columnDefinition = "BOOLEAN")
    private Boolean isActive;

    @OneToMany(mappedBy = "seller")
    private Set<PaymentMethod> paymentMethods;


    //Por ahora hago que tenga sólo una tienda, pero en el futuro puede tener más
    @OneToOne(mappedBy = "store")
    private Store store;

    

    //Si bien los productos finales, están dentro de una tienda, para poder hacer consultas más rápidas,
    //también los guardo acá
    @OneToMany(mappedBy = "seller")
    private List<FinalProduct> finalProducts;

    @OneToMany(mappedBy = "seller")
    private List<Sales> sales;


    public Seller(String name, String lastName,String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.isActive = true;
    }

}
