package com.ecommerce.managers.models;


import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "customization_type")
@Setter
@Getter
public class CustomizationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;


    @OneToMany(mappedBy = "customization_type")
    private List<PossibleCustomization> possibleCustomizations;

    /* @ManyToOne
    @JoinColumn(name = "possible_customization_id", referencedColumnName = "id")
    private PossibleCustomization possibleCustomizations; */


    public CustomizationType(String name){
        this.name = name;
    }
    
}