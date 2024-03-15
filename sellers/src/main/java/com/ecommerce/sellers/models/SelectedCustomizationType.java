package com.ecommerce.sellers.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class SelectedCustomizationType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @JoinColumn(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @OneToMany(mappedBy = "customization_type")
    private List<Personalization> possibleCustomizations;


    public SelectedCustomizationType(String name) {
        this.name = name;
    }
}
