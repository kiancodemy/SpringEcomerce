package com.Ecommerce.main.exception;

public class AlreadyExist extends RuntimeException{
    public AlreadyExist(String message) {
        super(message);
    }
}
