package com.Ecommerce.main.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String userNotFound) {
        super(userNotFound);
    }
}
