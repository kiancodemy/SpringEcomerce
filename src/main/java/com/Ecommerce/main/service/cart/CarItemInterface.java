package com.Ecommerce.main.service.cart;

public interface CarItemInterface {
    void addCartItem(Long cartId, Long productId,int quantity);
    void removeItemFromCart(Long cartId,Long productId);
    void updateItemQuantity(Long cartId,Long productId,int quantity);
}
