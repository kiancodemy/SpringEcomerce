package com.Ecommerce.main.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private BigDecimal totalAmount=BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CartItems> cartItems = new HashSet<>();

    @OneToOne
    @JoinColumn(name="user-id")
    private User user;

    public void addItem(CartItems item){
        cartItems.add(item);
        item.setCart(this);
        UpdateTotalAmount();
    }

    public void removeItem(CartItems item){
        cartItems.remove(item);
        item.setCart(null);
        UpdateTotalAmount();

    }
    public void UpdateTotalAmount(){
        this.totalAmount=this.cartItems.stream().map(item->{

            BigDecimal unitprice=item.getUnitPrice();
            if(unitprice==null){
                unitprice=BigDecimal.ZERO;
            }
            return unitprice.multiply(new BigDecimal(item.getQuantity()));

        }).reduce(BigDecimal.ZERO,BigDecimal::add);

    }




}
