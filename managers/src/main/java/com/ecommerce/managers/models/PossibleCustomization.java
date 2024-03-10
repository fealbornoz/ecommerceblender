package com.ecommerce.managers.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;




@Entity
@Table(name = "possible_customization")
public class PossibleCustomization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "isActive")
    private Boolean isActive;

    @OneToMany
    @JoinColumn(name = "customization_area_id", referencedColumnName = "id")
    private List<CustomizationArea> customizationAreas;

}



