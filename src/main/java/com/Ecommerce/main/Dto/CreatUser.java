package com.Ecommerce.main.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CreatUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
