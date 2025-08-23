package com.Ecommerce.main.repository;
import com.Ecommerce.main.model.Category;
import com.Ecommerce.main.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductByCategoryName(String category);
    List<Product> findProductByBrand(String brand);
    List<Product> findProductByCategoryNameAndBrand(String categoryName, String brandName);
    List<Product> findProductByName(String name);
    List<Product> findProductByBrandAndName(String brand, String name);
    Long countProductByBrandAndName(String brand, String name);
}
