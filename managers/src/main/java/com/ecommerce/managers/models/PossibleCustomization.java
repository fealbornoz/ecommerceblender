package com.ecommerce.managers.models;

import java.util.*;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;




@Entity
@Table(name = "possible_customization")
@Setter
@Getter
public class PossibleCustomization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "isActive")
    private Boolean isActive;



    @ManyToMany(mappedBy = "possibleCustomizations")
    private Set<ProductBase> productBases;

    @ManyToOne
    @JoinColumn(name = "customization_area_id", referencedColumnName = "id")
    private CustomizationArea customizationArea;

    @ManyToOne
    @JoinColumn(name = "customization_type_id", referencedColumnName = "id")
    private CustomizationType customizationType;


    

    public PossibleCustomization(CustomizationArea customizationAreas, CustomizationType customizationTypes){

        this.isActive = true;
        this.customizationArea = customizationAreas;
        this.customizationType = customizationTypes;


    }

    
    /* // Esto se obtiene dependiendo si hay un customizationArea seleccionado
    // Si hay un customizationArea seleccionado, se obtiene el customizationType
    @OneToMany
    private List<CustomizationType> customizationTypes; */




  /*   public List<CustomizationArea> getCustomizationAreas() {
        return this.customizationAreas;
    } */
}



