package com.ecommerce.sellers.models;

import java.util.List;
import java.util.Set;


import jakarta.persistence.*;

public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(name = "name", columnDefinition = "VARCHAR(15)")
    private String name;

    @Column(name = "lastName", columnDefinition = "VARCHAR(15)")
    private String lastName;

    @OneToMany(mappedBy = "seller")
    private Set<PaymentMethods> paymentMethods;


    //Por ahora hago que tenga sólo una tienda, pero en el futuro puede tener más
    @OneToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    

    //Si bien los productos finales, están dentro de una tienda, para poder hacer consultas más rápidas,
    //también los guardo acá
    @OneToMany(mappedBy = "seller")
    private List<FinalProduct> finalProducts;

    @OneToMany(mappedBy = "seller")
    private List<Sales> sales;

}
