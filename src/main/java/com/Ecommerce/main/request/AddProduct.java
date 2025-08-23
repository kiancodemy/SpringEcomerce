package com.Ecommerce.main.request;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class AddProduct {
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String categoryName;


}
