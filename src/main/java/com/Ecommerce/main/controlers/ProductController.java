package com.Ecommerce.main.controlers;
import com.Ecommerce.main.models.Product;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/product")
@RequiredArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping("getbyid/{id}")
    public ResponseEntity<ApiResponse> getById(Long id){
        try {
            Product product=productService.GetProdcutbyid(id);
            return ResponseEntity.ok(new ApiResponse("sucess",product));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));}
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProductByid(@PathVariable Long id){
        try {
            productService.GetProdcutbyid(id);
            return ResponseEntity.ok(new ApiResponse("sucesss",null));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));
        }
    }

    @DeleteMapping("/deletebyname")
    public ResponseEntity<ApiResponse> deleteProductByName(@RequestParam String productName){
        try {
            productService.deleteProdcutbyname(productName);
            return ResponseEntity.ok(new ApiResponse("sucess",null));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));
        }
    }

}
