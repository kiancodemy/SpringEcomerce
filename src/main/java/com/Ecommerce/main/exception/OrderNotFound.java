package com.Ecommerce.main.exception;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(String orderNotFound) {
        super(orderNotFound);
    }
}
