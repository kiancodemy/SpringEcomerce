package com.Ecommerce.main.request;
import com.Ecommerce.main.models.Category;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class AddRequest {
    private String brand;
    private String name;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;
}
