package com.Ecommerce.main.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

public class Product {

    private Long id;
    private String brand;
    private String name;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;
    private List<Image> images;
    }


