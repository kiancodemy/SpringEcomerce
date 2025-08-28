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
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;


    @ManyToOne
    @JoinColumn(name="product-id",referencedColumnName = "id",nullable = false)
    private Product product;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="cart-id",referencedColumnName = "id",nullable = false)
    private Cart cart;

    public void setTotalPrice() {
        this.totalPrice=this.unitPrice.multiply(new BigDecimal(quantity));
    }
}
