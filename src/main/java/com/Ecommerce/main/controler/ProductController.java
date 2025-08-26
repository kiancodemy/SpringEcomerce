package com.Ecommerce.main.controler;
import com.Ecommerce.main.Dto.ProductDto;
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
            if(products.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("it is empty",null));
            }
            List<ProductDto> productDtos=productService.covertToDtotolist(products);
            return ResponseEntity.ok(new ApiResponse("successfully Get all", productDtos));

        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(new ApiResponse(ex.getMessage(), null));


        }
    }

    @GetMapping("/getProdcutById/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable("id") Long id) {
        try {
            Product product = productService.getProductById(id);
            ProductDto productDto=productService.covertToDto(product);
            return  ResponseEntity.ok(new ApiResponse("successfully Get product", productDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }


    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable("id") Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("successfully Delete product", null));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }

    }
    @GetMapping("/getProductByCategory/{category}")
    public ResponseEntity<ApiResponse> getProductByCategory(@PathVariable("category") String category) {
        try {
            List<Product> allProduct=productService.getAllProductsByCategory(category);
            if(allProduct.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("it is empty",null));
            }
            List<ProductDto> productDtos=productService.covertToDtotolist(allProduct);
            return ResponseEntity.ok(new ApiResponse("successfully Get product by category", productDtos));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }


    }
    @GetMapping("/getProductByBrand/{brand}")
    public ResponseEntity<ApiResponse> getProductByBrand(@PathVariable("brand") String brand) {
        try {
            List<Product> allProduct=productService.getAllProductByBrand(brand);
            List<ProductDto> productDtos=productService.covertToDtotolist(allProduct);

            return ResponseEntity.ok(new ApiResponse("successfully Get product by brand",productDtos));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }


    }
    @GetMapping("/getProductByName/{name}")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable("name") String name) {
        try {
            List<Product> allProduct=productService.getProductByName(name);

            if(allProduct.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("it is empty",null));
            }

            List<ProductDto> productDtos=productService.covertToDtotolist(allProduct);

            return ResponseEntity.ok(new ApiResponse("successfully Get product by brand", productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/getProductByBrandAndName/{brand}/{name}")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@PathVariable String brand,@PathVariable String name) {
        try {
            List<Product> allProduct=productService.getProductByBrandAndName(brand,name);
            if(allProduct.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("it is empty",null));
            }
            List<ProductDto> productDtos=productService.covertToDtotolist(allProduct);
            return ResponseEntity.ok(new ApiResponse("successfully Get product by brand and name",  productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/countProdcut/{brand}/{name}")
    public ResponseEntity<ApiResponse> countProduct(@PathVariable String brand,@PathVariable String name) {
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
            ProductDto productDto=productService.covertToDto(createProduct);
            return ResponseEntity.ok(new ApiResponse("successfully add product", productDto));
        } catch (Exception e) {

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProduct product) {
        try {
            Product update=productService.updateProduct(product, id);
            ProductDto productDto=productService.covertToDto(update);
            return ResponseEntity.ok(new ApiResponse("successfully update product", productDto));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }

    }

}

