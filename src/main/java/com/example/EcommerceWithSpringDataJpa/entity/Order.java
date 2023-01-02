package com.example.EcommerceWithSpringDataJpa.entity;
import com.example.EcommerceWithSpringDataJpa.enums.OrderStatus;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name="orders")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "address_id")
    @ManyToOne
    private Address address;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order",fetch = FetchType.EAGER)
    private List<OrderDetails> orderDetails;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;
    @Column(name = "order_date")
    private LocalDate date;
    @Column(name = "status", nullable = false)
    private OrderStatus status;
    @Column(name = "total",nullable = false)
    private Double total;

    public Order(Customer customer, LocalDate date, OrderStatus status, Double total) {
        this.customer = customer;
        this.date = date;
        this.status = status;
        this.total = total;
    }
}
