package com.Ecommerce.main.services.category;

import com.Ecommerce.main.exception.ProdcutNotfound;
import com.Ecommerce.main.models.Category;
import com.Ecommerce.main.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CategoryService implements IntCategory{
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new ProdcutNotfound("OT FOUND"))

    }

    @Override
    public List<Category> GetAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    @Override
    public Category upateCategory(Category category) {
        return null;
    }

    @Override
    public Category createCategory(Category category) {
        return null;
    }
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(
                category -> categoryRepository.deleteById(id),
                () -> { throw new ProdcutNotfound("not found by this id"); }
        );
    }
}
