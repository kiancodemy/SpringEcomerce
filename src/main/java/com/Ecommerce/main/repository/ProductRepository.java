package com.Ecommerce.main.repository;
import com.Ecommerce.main.model.Category;
import com.Ecommerce.main.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryName(String categoryName);
}
