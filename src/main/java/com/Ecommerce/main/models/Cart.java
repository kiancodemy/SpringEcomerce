package com.Ecommerce.main.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal totalPrice=BigDecimal.ZERO;


    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    Set<CartItem> cartItems = new HashSet<>();

    public void addItem(CartItem cartItem){
        if(!cartItems.contains(cartItem)){
            cartItems.add(cartItem);
            cartItem.setCart(this);
            updateTotalPrice();
        }


    }

    public void removeItem(CartItem cartItem){
        if(cartItems.contains(cartItem)){
            cartItems.remove(cartItem);
            cartItem.setCart(null);
            updateTotalPrice();
        }


    }


    public void updateTotalPrice() {
        this.totalPrice=cartItems.stream().map(item->{BigDecimal unitprice=item.getUnitPrice();
        if(unitprice==null){
            unitprice=BigDecimal.ZERO;
        };
        return unitprice.multiply(BigDecimal.valueOf(item.getQuantity()));
        }).reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
