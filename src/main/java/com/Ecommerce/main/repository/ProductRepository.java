package com.Ecommerce.main.repository;

import com.Ecommerce.main.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByName(String name);
    List<Product> findByCategory(String category);
    List<Product> findByBrand(String brand);
    List<Product> findByCategoryNameAndBrand(String category, String brand);
    List<Product> findProductByBrand(String brand);
    List<Product> findProducgtByName(String name);
    Long CountProductsByCategoryName(String category);
    Long CountProductsByBrand(String brand);
    Void DeleteProductByName(String name);

}
