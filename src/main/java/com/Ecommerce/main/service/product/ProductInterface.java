package com.Ecommerce.main.service.product;

import com.Ecommerce.main.Dto.ProductDto;
import com.Ecommerce.main.model.Product;
import com.Ecommerce.main.request.AddProduct;
import com.Ecommerce.main.request.UpdateProduct;

import java.util.List;

public interface ProductInterface {
    Product addProduct(AddProduct product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateProduct product, Long id);
    List<Product> getAllProductsByCategory(String category);
    List<Product> getAllProductByBrand(String brand);

    List<Product> getAllProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductByName(String name);
    List<Product> getProductByBrandAndName(String brand,String name);
    Long CountProductByBrandAndName(String brand,String name);


    ProductDto covertToDto(Product product);

    List<ProductDto> covertToDtotolist(List<Product> products);
}
