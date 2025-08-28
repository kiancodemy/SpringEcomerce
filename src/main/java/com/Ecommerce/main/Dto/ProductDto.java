package com.Ecommerce.main.Dto;
import com.Ecommerce.main.model.Category;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private Category category;
    private List<ImageDto> images;
}
