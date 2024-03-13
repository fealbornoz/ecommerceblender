package com.ecommerce.managers.models;

import java.util.List;

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


    @OneToMany(mappedBy = "customization_type")
    private List<PossibleCustomization> possibleCustomizations;


    public CustomizationArea(String name){
        this.name = name;
    }
   

    
}
