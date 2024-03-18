package com.ecommerce.buyers.models;

import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce.buyers.dtos.OrderDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private Buyer buyer;

    @Column(name = "store_id")
    private Integer store_id;

    @Column(name = "payment_method", columnDefinition = "VARCHAR(15)")
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_order", columnDefinition = "VARCHAR(15)")
    private StateOrder stateOrder;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order(OrderDTO orderDTO, Buyer buyer) {
        this.date = LocalDateTime.now();
        this.store_id = orderDTO.getStore_id();
        this.paymentMethod = orderDTO.getPaymentMethod();
        this.stateOrder = StateOrder.BILLED;
        this.buyer = buyer;
    }

    public void addOrderItem(OrderItem orderItem) {
		this.orderItems.add(orderItem);
        orderItem.setOrder(this);
        
	}

    

}
