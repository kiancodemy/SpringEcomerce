package com.Ecommerce.main.Dto;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class CartDto {
    private Long id;
    private BigDecimal totalAmount;

}
