package com.Ecommerce.main.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String filetype;
    private String filepath;

    @Lob
    private Blob image;
    private String downloadUrl;

    @ManyToOne
    @JoinColumn(name="product-id",nullable = false,unique = true)
    private Product product;
}
