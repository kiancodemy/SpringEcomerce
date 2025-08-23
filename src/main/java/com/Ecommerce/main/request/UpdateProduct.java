package com.Ecommerce.main.request;
import com.Ecommerce.main.model.Category;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class UpdateProduct {
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String category;
}
