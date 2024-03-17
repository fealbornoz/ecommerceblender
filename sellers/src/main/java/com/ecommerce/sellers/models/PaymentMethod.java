package com.ecommerce.sellers.models;


import com.ecommerce.sellers.dtos.PaymentMethodDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "payment_method")
@Setter
@Getter
public class PaymentMethod {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="name", columnDefinition = "VARCHAR(20)")
    private String name;




    //Esto es bidireccional
    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;



    public PaymentMethod(PaymentMethodDTO paymentMethodDTO) {
        this.name = paymentMethodDTO.getName();
    }


}
