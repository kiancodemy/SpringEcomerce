package com.Ecommerce.main.service.category;
import com.Ecommerce.main.exception.AlreadyExist;
import com.Ecommerce.main.exception.CategoryNotFound;
import com.Ecommerce.main.model.Category;
import com.Ecommerce.main.repository.CategoryRepository;
import com.Ecommerce.main.request.AddCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService implements CategoryInterface{
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new CategoryNotFound("category not found"));

    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoriesByName(name);
    }

    @Override
    public void DeleteCategoryById(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(c->categoryRepository.deleteById(id),()->{throw new CategoryNotFound("category not found");});

    }


    @Override
    public Category addCategory(AddCategory category) {
        return Optional.of(category).filter(c->!categoryRepository.existsByName(category.getName())).map(c->categoryRepository.save(new Category(category.getName()))).orElseThrow(()->new AlreadyExist("already exist"));
    }

    @Override
    public Category updateCategory(AddCategory category, Long id) {
        return categoryRepository.findById(id).map(c->{c.setName(category.getName());return categoryRepository.save(c);}).orElseThrow(()->new CategoryNotFound("NOT FOUND"));

    }

}
