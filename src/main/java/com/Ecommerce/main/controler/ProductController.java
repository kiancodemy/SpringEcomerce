package com.Ecommerce.main.controler;
import com.Ecommerce.main.model.Product;
import com.Ecommerce.main.request.AddProduct;
import com.Ecommerce.main.request.UpdateProduct;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getAlProducts")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try{
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("successfully Get all", products));

        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), null));


        }
    }

    @GetMapping("/getProdcutById/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable("id") Long id) {
        try {
            Product product = productService.getProductById(id);
            return  ResponseEntity.ok(new ApiResponse("successfully Get product", product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }


    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable("id") Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("successfully Delete product", productService.getProductById(id)));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }

    }
    @GetMapping("/getProductByCategory")
    public ResponseEntity<ApiResponse> getProductByCategory(@RequestParam("category") String category) {
        try {
            List<Product> allProduct=productService.getAllProductsByCategory(category);
            return ResponseEntity.ok(new ApiResponse("successfully Get product by category", allProduct));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }


    }
    @GetMapping("/getProductByBrand")
    public ResponseEntity<ApiResponse> getProductBybrand(@RequestParam("brand") String brand) {
        try {
            List<Product> allProduct=productService.getAllProductByBrand(brand);
            return ResponseEntity.ok(new ApiResponse("successfully Get product by brand", allProduct));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }


    }
    @GetMapping("/getProductByName")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam("name") String name) {
        try {
            List<Product> allProduct=productService.getProductByName(name);
            return ResponseEntity.ok(new ApiResponse("successfully Get product by brand", allProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/getProductByBrandAndName")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brand,@RequestParam String name) {
        try {
            List<Product> allProduct=productService.getProductByBrandAndName(brand,name);
            return ResponseEntity.ok(new ApiResponse("successfully Get product by brand and name", allProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/countProdcut")
    public ResponseEntity<ApiResponse> countProduct(@RequestParam String brand,@RequestParam String name) {
        try {
            Long count =productService.CountProductByBrandAndName(brand,name);
            return ResponseEntity.ok(new ApiResponse("successfully count product", count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }}

    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProduct product) {
        try {
            Product createProduct=productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("successfully add product", createProduct));
        } catch (Exception e) {

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProduct product) {
        try {
            Product update=productService.updateProduct(product, id);
            return ResponseEntity.ok(new ApiResponse("successfully update product", update));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }

    }

}

