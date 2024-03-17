package com.ecommerce.managers.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "customization_area")
@Setter
@Getter
public class CustomizationArea {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;


    @OneToOne
    @JoinColumn(name = "possible_customization_id", referencedColumnName = "id")
    private PossibleCustomization possibleCustomization;


    public CustomizationArea(String name){
        this.name = name;
    }
   

    
}
