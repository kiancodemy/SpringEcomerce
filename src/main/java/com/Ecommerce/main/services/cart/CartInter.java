package com.Ecommerce.main.services.cart;

import com.Ecommerce.main.models.Cart;

import java.math.BigDecimal;

public interface CartInter {
    Cart getCart(Long id);
    void clear(Long id );
    BigDecimal getTotalPrice(Long id);
}
