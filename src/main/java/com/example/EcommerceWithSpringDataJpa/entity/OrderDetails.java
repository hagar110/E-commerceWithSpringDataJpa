package com.example.EcommerceWithSpringDataJpa.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Product product;
    @Column(name = "product_quantity")
    @NotNull
    private int productQuantity;


    public OrderDetails(Order order,int productQuantity) {
        this.order = order;
        this.productQuantity = productQuantity;
    }

    public OrderDetails(Order order,Product product ,int productQuantity) {
        this.order = order;
        this.productQuantity = productQuantity;
        this.product=product;
    }
}
