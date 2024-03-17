package com.ecommerce.managers.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "customization_type")
@Setter
@Getter
public class CustomizationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;


    @ManyToOne
    @JoinColumn(name = "possible_customization_id", referencedColumnName = "id")
    private PossibleCustomization possibleCustomization;

    /* @ManyToOne
    @JoinColumn(name = "possible_customization_id", referencedColumnName = "id")
    private PossibleCustomization possibleCustomizations; */


    public CustomizationType(String name){
        this.name = name;
    }
    
}