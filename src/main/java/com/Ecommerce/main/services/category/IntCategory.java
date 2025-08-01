package com.Ecommerce.main.services.category;

import com.Ecommerce.main.models.Category;

import java.util.List;

public interface IntCategory {

    Category getCategoryById(Long id);
    List<Category> GetAllCategory();
    Category getCategoryByName(String name);
    Category upateCategory(Category category);
    Category createCategory(Category category);
    void deleteCategory(Long id);


}
