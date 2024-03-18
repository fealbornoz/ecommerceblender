package com.ecommerce.buyers.models;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order")
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue
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

    @Enumerated
    @Column(name = "state_order", columnDefinition = "VARCHAR(15)")
    private StateOrder stateOrder;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order(Buyer buyer, Integer store_id, String paymentMethod) {
        this.date = LocalDateTime.now();
        this.buyer = buyer;
        this.store_id = store_id;
        this.paymentMethod = paymentMethod;
        this.stateOrder = StateOrder.BILLED;
        
    }

    public void addOrderItem(OrderItem orderItem) {
		this.orderItems.add(orderItem);
        orderItem.setOrder(this);
        
	}

    

}
