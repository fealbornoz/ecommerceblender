package com.ecommerce.sellers.models;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.sellers.dtos.StoreDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "store")
@Setter
@Getter
public class Store {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(name = "name", columnDefinition = "VARCHAR(30)")
    private String name;

    
    @OneToMany(mappedBy = "store")
    private List<Publication> publications;



    @OneToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;


    public Store(StoreDTO store) {
        this.name = store.getName();
        this.publications = new ArrayList<>();
    }


    public void addPublication(Publication publication){
        this.publications.add(publication);
        publication.setStore(this);
    }



}
