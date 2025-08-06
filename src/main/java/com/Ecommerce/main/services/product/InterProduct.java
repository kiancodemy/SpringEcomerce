package com.Ecommerce.main.services.product;

import com.Ecommerce.main.Dto.ProductDto;
import com.Ecommerce.main.models.Product;
import com.Ecommerce.main.request.AddRequest;
import com.Ecommerce.main.request.UpdateRequest;

import java.util.List;

public interface InterProduct {

    Product GetProdcutbyid(Long id);
    void deleteProdcutById(Long id);
    void deleteProdcutbyname(String name);
    void UpdateProdcutbyid(UpdateRequest prodcut, Long id);
    Product addProduct(AddRequest addRequest);
    List<Product> FindByName(String name);
    List<Product> GetAllProdcutsByCategory(String category);
    List<Product> GetAllProdcuts();
    List<Product> GetAllProdcutsByBrand(String brand);
    List<Product> GetProdcutByCategoryAndBrand(String category,String brand);
    List<Product> GetProdcutByBrandAndName(String brand,String name);


    Long CountProductsByBrandandname(String brand,String name);


    List<ProductDto> convertToDtoList(List<Product> products);

    ProductDto convertToDt(Product product);
}
