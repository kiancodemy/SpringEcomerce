package com.Ecommerce.main.service.product;

import com.Ecommerce.main.exception.ProdcutNotFound;
import com.Ecommerce.main.model.Product;
import com.Ecommerce.main.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService implements ProductInterface {
    private final ProductRepository productRepository;


    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProdcutNotFound("prodcut not found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,()->{throw new ProdcutNotFound("prodcut not found");});

    }

    @Override
    public void updateProduct(Product product, Long id) {

    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getAllProductByBrand(String brand) {
        return List.of();
    }

    @Override
    public List<Product> getAllProductsByCategoryAndBrand(String category, String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductByName(String name) {
        return List.of();
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return List.of();
    }

    @Override
    public Long CountProductByBrandAndName(String brand, String name) {
        return 0L;
    }
}
