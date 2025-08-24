package com.Ecommerce.main.controler;
import com.Ecommerce.main.model.Product;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getall")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try{
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("success", products));

        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), null));


        }
    }

    /*@GetMapping("/getProdcutById/{id}")
    public ResponseEntity<ApiResponse> getProductById(@RequestParam String id) {

    }*/
}
