package com.Ecommerce.main.Dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class OrderDto {

    private Long id;
    private LocalDate orderDate;
    private BigDecimal orderTotalAmount;
    private OrderStatus orderstatus;
    private Set<OrderItemDto> orderItems = new HashSet<>();

}
