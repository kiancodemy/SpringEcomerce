package com.Ecommerce.main.service.product;

import com.Ecommerce.main.model.Product;

import java.util.List;

public interface ProductInterface {
    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Product product,Long id);
    List<Product> getAllProductsByCategory(String category);
    List<Product> getAllProductByBrand(String brand);
    List<Product> getAllProductsByCategoryAndBrand(String category,String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductByBrandAndName(String brand,String name);
    Long CountProductByBrandAndName(String brand,String name);






}
