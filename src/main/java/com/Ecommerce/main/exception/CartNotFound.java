package com.Ecommerce.main.exception;

public class CartNotFound extends RuntimeException {
    public CartNotFound(String cartNotFound) {
        super(cartNotFound);
    }
}
