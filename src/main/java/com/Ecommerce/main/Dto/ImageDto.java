package com.Ecommerce.main.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ImageDto {
    private Long id;
    private String filename;
    private String downloadurl;
}
