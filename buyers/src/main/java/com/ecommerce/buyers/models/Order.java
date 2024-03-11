package com.ecommerce.buyers.models;


import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name = "order")
public class Order {
    
    @Id
	@GeneratedValue
	private Integer id;


    @Column(name = "store_id")
	private Integer store_id;
	
    
    @Column(name = "payment_method", columnDefinition = "VARCHAR(15)")
	private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private Buyer buyer;

    @Column(name = "date")
	private LocalDateTime date;


    @Enumerated
    @Column(name = "state_order", columnDefinition = "VARCHAR(15)")
    private StateOrder stateOrder;
 

    @OneToMany(mappedBy = "order_item")
	private List<OrderItem> orderItem;	

}
