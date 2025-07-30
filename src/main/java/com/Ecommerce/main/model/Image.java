package com.Ecommerce.main.model;

import java.sql.Blob;

public class Image {
    private Long id;
    private String filename;
    private String filetype;
    private Blob image;
    private String downloadurl;

    private Product product;
}
