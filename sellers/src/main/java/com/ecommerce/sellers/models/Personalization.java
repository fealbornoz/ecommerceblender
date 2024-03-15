package com.ecommerce.sellers.models;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personalization")
@Setter
@Getter
public class Personalization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @JoinColumn(name = "isActive_possible_customization")
    private Boolean isActive;

  

    @ManyToMany(mappedBy = "personalization")
    private Set<FinalProduct> finalProducts;

    @ManyToOne
    @JoinColumn(name = "selected_customization_area_id", referencedColumnName = "id")
    private SelectedCustomizationArea selectedCustomizationArea;

    @ManyToOne
    @JoinColumn(name = "selected_customization_type_id", referencedColumnName = "id")
    private SelectedCustomizationType selectedCustomizationType;



    public Personalization( SelectedCustomizationArea selectedCustomizationArea, SelectedCustomizationType selectedCustomizationType) {
        this.isActive = true;
        this.selectedCustomizationArea = selectedCustomizationArea;
        this.selectedCustomizationType = selectedCustomizationType;
    }
} 
