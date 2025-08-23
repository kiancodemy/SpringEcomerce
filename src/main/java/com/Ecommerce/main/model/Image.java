package com.Ecommerce.main.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String filepath;

    @Lob
    private byte[] image;
    private String downloadUrl;

    @ManyToOne
    @JoinColumn(name="product-id",nullable = false,unique = true)
    private Product product;
}
