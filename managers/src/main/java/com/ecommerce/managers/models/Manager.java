package com.ecommerce.managers.models;

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

    @Column(name = "lastName", columnDefinition = "VARCHAR(50)") 
    private String lastName;
    
    @OneToMany
    @JoinColumn(name = "product_base_id", referencedColumnName = "id")
    private ProductBase productBase;

    
}

