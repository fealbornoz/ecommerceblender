package com.ecommerce.sellers.models;



import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "isActive_possible_customization")
    private Boolean isActive;


    @OneToOne(mappedBy = "personalization")
    private SelectedCustomizationArea selectedCustomizationArea;

    @OneToMany(mappedBy = "personalization")
    private List<SelectedCustomizationType> selectedCustomizationType;

    @ManyToOne
    @JoinColumn(name = "final_product_id", referencedColumnName = "id")
    private FinalProduct finalProduct;

    public Personalization(SelectedCustomizationArea selectedCustomizationArea) {
        this.isActive = true;
        this.selectedCustomizationArea = selectedCustomizationArea;
        selectedCustomizationArea.setPersonalization(this);
        this.selectedCustomizationType = new ArrayList<>();
    }

    public void addSelectedCustomizationType(SelectedCustomizationType selectedCustomizationType) {
        this.selectedCustomizationType.add(selectedCustomizationType);
        selectedCustomizationType.setPersonalization(this);
    }

}
