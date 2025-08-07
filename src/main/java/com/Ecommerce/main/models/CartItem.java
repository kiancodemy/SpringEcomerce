package com.Ecommerce.main.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name="product-id",referencedColumnName = "id",foreignKey = @ForeignKey(name="product-id"))
    private Product product;

    @ManyToOne
    @JoinColumn(name="card-id",referencedColumnName = "id",foreignKey = @ForeignKey(name="card-id"))
    private Cart cart;

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = this.unitPrice.multiply(totalPrice);
    }
}
