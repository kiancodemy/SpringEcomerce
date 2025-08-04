package com.Ecommerce.main.services.category;
import com.Ecommerce.main.exception.AlreadyExist;
import com.Ecommerce.main.exception.ResourceNotFound;
import com.Ecommerce.main.models.Category;
import com.Ecommerce.main.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService implements IntCategory{
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFound("category not FOUND"));

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
    public Category addCategory(Category category) {
        return Optional.ofNullable(category).filter(c->!categoryRepository.existsByName(c.getName())).map(categoryRepository::save).orElseThrow(()-> new AlreadyExist(category.getName()+"already exists"));
    }

    @Override
    public Category upateCategory(Category category,Long id) {
        return Optional.ofNullable(getCategoryById(id)).map(c->{c.setName(category.getName());return categoryRepository.save(c);}).orElseThrow(()->new ResourceNotFound("NOT FOUND BY ID"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(
                category -> categoryRepository.deleteById(id),
                () -> { throw new ResourceNotFound("not found by this id"); }
        );
    }
}
