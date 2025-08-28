package com.Ecommerce.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private int quantity;
    private BigDecimal price;


    @ManyToOne
    @JoinColumn(name="product-id",nullable = false)
    private Product product;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="order-id",nullable = false)
    private Order order;

    public OrderItem(int quantity, BigDecimal price, Product product, Order order) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.order = order;
    }
}
