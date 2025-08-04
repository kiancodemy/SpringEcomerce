package com.Ecommerce.main.controlers;
import com.Ecommerce.main.models.Category;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.services.category.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/allcategory")
    public ResponseEntity<ApiResponse> getall(){
        try {
            List <Category>all= categoryService.GetAllCategory();
            return ResponseEntity.ok().body(new ApiResponse("success",all));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("success",e.getMessage()));
        }
    }

    @GetMapping("getbyid/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try {
            Category category=categoryService.getCategoryById(id);
            return ResponseEntity.ok().body(new ApiResponse("success",category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("success",e.getMessage()));
        }

    }

    public ResponseEntity<ApiResponse> getCategoryByName(String name){
        try {
            Category category=categoryService.getCategoryByName(name);
            return ResponseEntity.ok().body(new ApiResponse("success",category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("sfailed",e.getMessage()));
        }
    }

    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().body(new ApiResponse("success",categoryService.getCategoryById(id)));
        } catch (Exception e) {
           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed to delte",e.getMessage()));
        }
    }

    @PostMapping("/addCategory")
    public ResponseEntity<ApiResponse> addCategory(@Valid @NotNull(message = "it cant be null") @RequestBody Category category) {
        try {
            Category find=categoryService.addCategory(category);
            return ResponseEntity.ok().body(new ApiResponse("success",find));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("sfailed",e.getMessage()));
        }
    }

    @PutMapping("/updated/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody Category category, @RequestParam Long id){
        try {
            Category create=categoryService.upateCategory(category, id);
            return ResponseEntity.ok().body(new ApiResponse("success",create));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("sfailed",e.getMessage()));
        }

    }





}
