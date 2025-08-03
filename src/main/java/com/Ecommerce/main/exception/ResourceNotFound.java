package com.Ecommerce.main.exception;

public class ResourceNotFound extends  RuntimeException{
    public ResourceNotFound(String otFound) {
        super(otFound);
    }
}
