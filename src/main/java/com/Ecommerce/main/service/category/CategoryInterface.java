package com.Ecommerce.main.service.category;

import com.Ecommerce.main.model.Category;
import com.Ecommerce.main.request.AddCategory;

import java.util.List;

public interface CategoryInterface {

    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    void DeleteCategoryById(Long id);
    Category addCategory(AddCategory category);
    Category updateCategory(AddCategory  category, Long id);


}
