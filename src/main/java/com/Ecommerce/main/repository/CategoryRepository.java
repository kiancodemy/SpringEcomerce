package com.Ecommerce.main.repository;

import com.Ecommerce.main.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
    Category findByCategoryName(String name);
}
