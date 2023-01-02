package com.example.EcommerceWithSpringDataJpa.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ShoppingCartProducts")
public class ShoppingCartProducts {
    public ShoppingCartProducts() {
    }

    public ShoppingCartProducts(int id, int productQuantity, Product product, User user) {
        this.id = id;
        this.productQuantity = productQuantity;
        this.product = product;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @NotNull
    @Column(name = "product_quantity", nullable = false)
    private int productQuantity;

    @OneToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public ShoppingCartProducts(int productQuantity, Product product, User user) {
        this.productQuantity = productQuantity;
        this.product = product;
        this.user = user;
    }
}