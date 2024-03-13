package com.ecommerce.managers.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "manager")
@Setter
@Getter
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "name", columnDefinition = "VARCHAR(15)")
    private String name;

    @Column(name = "lastName", columnDefinition = "VARCHAR(15)") 
    private String lastName;
    
    @OneToMany(mappedBy = "manager")
    private List<ProductBase> productsBase;



     public Manager(String name, String lastName ){
        this.name = name;
        this.lastName = lastName;
    }

    
}

