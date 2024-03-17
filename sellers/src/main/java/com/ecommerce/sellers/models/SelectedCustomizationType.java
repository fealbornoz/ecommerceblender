package com.ecommerce.sellers.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "personalization_id", referencedColumnName = "id")
    private Personalization possibleCustomizations;


    public SelectedCustomizationType(String name) {
        this.name = name;
    }
}
