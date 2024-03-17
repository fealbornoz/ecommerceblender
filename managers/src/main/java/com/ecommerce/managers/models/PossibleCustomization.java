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



    @OneToOne(mappedBy = "possibleCustomization")
    private CustomizationArea customizationArea;

    @OneToMany(mappedBy = "possibleCustomization")
    private List<CustomizationType> customizationType;

    @ManyToOne
    @JoinColumn(name = "product_base_id", referencedColumnName = "id")
    private ProductBase productBase;


    

    public PossibleCustomization(CustomizationArea customizationArea){

        this.isActive = true;
        this.customizationArea = customizationArea ;
        customizationArea.setPossibleCustomization(this);
    }

    public void addCustomizationType(CustomizationType customizationType){
        this.customizationType.add(customizationType);
        customizationType.setPossibleCustomization(this);
    }

   








    
    /* // Esto se obtiene dependiendo si hay un customizationArea seleccionado
    // Si hay un customizationArea seleccionado, se obtiene el customizationType
    @OneToMany
    private List<CustomizationType> customizationTypes; */




  /*   public List<CustomizationArea> getCustomizationAreas() {
        return this.customizationAreas;
    } */
}



