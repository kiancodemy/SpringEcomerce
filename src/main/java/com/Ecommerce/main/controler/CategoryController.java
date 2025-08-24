package com.Ecommerce.main.controler;
import com.Ecommerce.main.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

}
