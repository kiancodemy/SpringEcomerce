package com.Ecommerce.main.request;

import lombok.Data;

@Data
public class AddCategory {
    private String name;
    public AddCategory(String name) {
        this.name = name;
    }
}
