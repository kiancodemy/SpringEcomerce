package com.Ecommerce.main.service.cart;

import com.Ecommerce.main.model.User;

public interface CarItemInterface {
    void addCartItem(Long cartId, Long productId,int quantity);
    void removeItemFromCart(Long cartId,Long productId);
    void updateItemQuantity(Long cartId,Long productId,int quantity);

    Long getUserCardI(User user);

    Long findUserCard(User user);
}
