package com.ecommerce.sellers.models;


import com.ecommerce.sellers.dtos.SelectedCustomizationAreaDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class SelectedCustomizationArea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;


    @JoinColumn(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;


    @OneToOne
    @JoinColumn(name = "personalization_id", referencedColumnName = "id")
    private Personalization personalization;


    public SelectedCustomizationArea(SelectedCustomizationAreaDTO selectedCustomizationAreaDTO) {
        this.name = selectedCustomizationAreaDTO.getName();
    }



}
