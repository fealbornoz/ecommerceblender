package com.ecommerce.managers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;




@Entity
@Table(name = "possible_customization")
@Setter
@Getter
public class PossibleCustomization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    


    

}



