package com.example.EcommerceWithSpringDataJpa.entity;


import com.example.EcommerceWithSpringDataJpa.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="orders")
public class Order{
    public Order() {
    }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
