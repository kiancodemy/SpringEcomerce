package com.Ecommerce.main.Dto;
import com.Ecommerce.main.model.Order;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private CartDto cart;
    private List<OrderDto> orders=new ArrayList<>();
}
