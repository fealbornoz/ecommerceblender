package com.ecommerce.managers.models;


import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_base")
@Setter
@Getter
public class ProductBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR(255)")
    private String description;

    @Column(name = "price")
    private Double price;


    @Column(name = "manufacturingTime")
    private Integer manufacturingTime;

    @Column(name = "isActive")
    private Boolean isActive;

    // Crea una tabla intermedia para la relación muchos a muchos de productBase y possibleCustomization
    // Aunque no se especifique el nombre de la tabla intermedia, se crea una tabla con el nombre de ambas tablas
    // Lo hago para que quede más claro
    @ManyToMany
    @JoinTable(
        name = "product_base_possible_customization",
        joinColumns = @JoinColumn(name = "product_base_id"),
        inverseJoinColumns = @JoinColumn(name = "possible_customization_id")
    )
    private Set<PossibleCustomization> possibleCustomizations;



    // Acá hay bidireccionalidad
    @ManyToOne(optional = false)
    @JoinColumn(name = "gestorId", referencedColumnName = "id")
    private Manager manager;
}
