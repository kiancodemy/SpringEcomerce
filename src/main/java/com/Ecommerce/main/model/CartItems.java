package com.Ecommerce.main.model;
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
    @JoinColumn(name="prodcut-id",referencedColumnName = "id",nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name="card-id",referencedColumnName = "id",nullable = false)
    private Cart cart;

    public void setTotalPrice() {
        this.totalPrice=this.unitPrice.multiply(new BigDecimal(quantity));
    }
}
