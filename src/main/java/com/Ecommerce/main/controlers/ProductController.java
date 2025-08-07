package com.Ecommerce.main.controlers;
import com.Ecommerce.main.Dto.ProductDto;
import com.Ecommerce.main.exception.ProdcutNotfound;
import com.Ecommerce.main.models.Product;
import com.Ecommerce.main.request.AddRequest;
import com.Ecommerce.main.request.UpdateRequest;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("getbyid/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id){
        try {
            Product product=productService.GetProdcutbyid(id);
            ProductDto dto=productService.convertToDt(product);
            return ResponseEntity.ok(new ApiResponse("sucess",dto));
        } catch (ProdcutNotfound e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));}
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable Long id){
        try {
            productService.deleteProdcutById(id);
            return ResponseEntity.ok(new ApiResponse("sucesss",null));
        }
        catch (ProdcutNotfound e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));
        }
    }

    @DeleteMapping("/deletebyname/{productName}")
    public ResponseEntity<ApiResponse> deleteProductByName(@PathVariable String productName){
        try {
            productService.deleteProdcutbyname(productName);
            return ResponseEntity.ok(new ApiResponse("success",null));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateById(@RequestBody UpdateRequest product,@PathVariable Long id){
        try {
            productService.UpdateProdcutbyid(product,id);
            return ResponseEntity.ok(new ApiResponse("sucess",null));
        } catch (ProdcutNotfound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/addnew")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddRequest addRequest){
        try {
            Product newProduct=productService.addProduct(addRequest);
            ProductDto dto=productService.convertToDt(newProduct);
            return ResponseEntity.ok(new ApiResponse("sucess",dto));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));
        }
    }

    @GetMapping("/getbyname")
    public ResponseEntity<ApiResponse> getByName(@RequestParam  String name){
        try {
            List<Product> all=productService.FindByName(name);

            if(all.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("There is not product",null));

            }
            List<ProductDto> dto=productService.convertToDtoList(all);
            return ResponseEntity.ok(new ApiResponse("sucess",dto));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));
        }

    }

    @GetMapping("/getbycategory")
    public ResponseEntity<ApiResponse> getByCategory(@RequestParam String name){
        try {
            List<Product> all=productService.GetAllProdcutsByCategory(name);

            if(all.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("There is not product",null));

            }
            List<ProductDto> dto=productService.convertToDtoList(all);
            return ResponseEntity.ok(new ApiResponse("sucess",dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));
        }
    }
    @GetMapping("/geAll")
    public ResponseEntity<ApiResponse> getAll(){
        try {
            List<Product> all=productService.GetAllProdcuts();

            if(all.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("There is not product",null));

            }
            List<ProductDto> dto=productService.convertToDtoList(all);
            return ResponseEntity.ok(new ApiResponse("sucess",dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));
        }
    }

    @GetMapping("/getbybrand")
    public ResponseEntity<ApiResponse> getAllByBrand(@RequestParam String brand){
        try {
            List<Product> all= productService.GetAllProdcutsByBrand(brand);

            if(all.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("There is not product",null));

            }

            List<ProductDto> dto=productService.convertToDtoList(all);
            return ResponseEntity.ok(new ApiResponse("sucess",dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));


        }
    }

    @GetMapping("/getbycategoryandbrad")
    public ResponseEntity<ApiResponse> getAllByCategoryAndBrand(@RequestParam String category  , @RequestParam String brand){
        try {
            List<Product> all= productService.GetProdcutByCategoryAndBrand(category,brand);
            List<ProductDto> dto=productService.convertToDtoList(all);
            if(all.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("There is not product",null));

            }
            return ResponseEntity.ok(new ApiResponse("sucess",dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));


        }
    }

    @GetMapping("/getbybrandAndName")
    public ResponseEntity<ApiResponse> getAllByBrandAndName(@RequestParam String brand,@RequestParam String name){
        try {
            List<Product> all= productService.GetProdcutByBrandAndName(brand,name);

            if(all.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("There is not product",null));

            }
            List<ProductDto> dto=productService.convertToDtoList(all);
            return ResponseEntity.ok(new ApiResponse("sucess",dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));
        }
    }

    @GetMapping("/countBrand")
    public ResponseEntity<ApiResponse> CountProductsByBrandandname(@RequestParam String brand,@RequestParam String name){
        try {
            Long count=productService.CountProductsByBrandandname(brand,name);
            return ResponseEntity.ok(new ApiResponse("sucess",count));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));
        }

    }




}
