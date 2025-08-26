package com.Ecommerce.main.service.cart;

import com.Ecommerce.main.model.Cart;

import java.math.BigDecimal;

public interface CartInterface {

    Cart getCartById(Long id);
    void clearCart(Long cartId);
    BigDecimal getTotalPrice(Long cartId);

}
