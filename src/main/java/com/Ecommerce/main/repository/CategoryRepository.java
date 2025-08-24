package com.Ecommerce.main.repository;
import com.Ecommerce.main.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String categoryName);

    Category getCategoriesByName(String name);

    boolean existsByName(String name);
}

