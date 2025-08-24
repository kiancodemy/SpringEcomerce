package com.Ecommerce.main.controler;
import com.Ecommerce.main.model.Category;
import com.Ecommerce.main.request.AddCategory;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/GetAllCategories")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try {
            List<Category> AllCategories= categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("sucessfully fetched All",AllCategories));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/CategoryById/{id}")
    public ResponseEntity<ApiResponse> findCategoryById(@PathVariable("id") Long id){
        try {
            Category findCategory=categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("sucessfulle fetched by id ",findCategory));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/DeleteCategory/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Long id){
        try {
            categoryService.DeleteCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("sucessfully deleted",null));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }

    }
    @GetMapping("/CategoryByName")
    public ResponseEntity<ApiResponse> findCategoryByName(@RequestParam("name") String name){
        try {
            Category findCategory=categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("sucessful fetched",findCategory));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PostMapping("/addCategory")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody AddCategory category){
        try {
            Category category1=categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("sucessfully added",category1));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody AddCategory category,@PathVariable("id") Long id){
        try {
            Category find=categoryService.updateCategory(category, id);
            return ResponseEntity.ok().body(new ApiResponse("sucessfully updated",find));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }

    }

}
