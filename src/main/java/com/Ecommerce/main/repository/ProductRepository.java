package com.Ecommerce.main.repository;

import com.Ecommerce.main.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByName(String name);


    List<Product> findByCategoryName(String categoryName);
    List<Product> findByBrand(String brand);
    List<Product> findByCategoryNameAndBrand(String category, String brand);
    List<Product> findProductByBrand(String brand);


    List<Product> findProductByName(String name);
    Long countProductByBrandAndName(String brand,String name);

    Void deleteProductByName(String name);

}
