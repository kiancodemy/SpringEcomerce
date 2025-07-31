package com.Ecommerce.main.services.product;
import com.Ecommerce.main.exception.ProdcutNotfound;
import com.Ecommerce.main.models.Category;
import com.Ecommerce.main.models.Product;
import com.Ecommerce.main.repository.ProductRepository;
import com.Ecommerce.main.repository.CategoryRepository;
import com.Ecommerce.main.request.AddRequest;
import com.Ecommerce.main.request.UpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements InterProduct{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product GetProdcutbyid(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProdcutNotfound("prodcut not found"));

    }

    @Override
    public void deleteProdcutById(Long id) {
        productRepository.findById(id).ifPresentOrElse(c -> productRepository.deleteById(c.getId()),()->{throw new ProdcutNotfound("prodcut not found");});
    }

    @Override
    public void deleteProdcutbyname(String name) {
         productRepository.DeleteProductByName(name);

    }

    @Override
    public void UpdateProdcutbyid(UpdateRequest prodcut, Long id) {
        productRepository.findById(id).map(c->existing(c,prodcut)).map(productRepository::save).orElseThrow(()->new ProdcutNotfound("product not found"));

    }

    private Product existing(Product existing,UpdateRequest request){
        existing.setName(request.getName());
        existing.setBrand(request.getBrand());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setInventory(request.getInventory());
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existing.setCategory(category);
        return existing;


    }

    @Override
    public Product addProduct(AddRequest addRequest) {
        Category category = Optional.ofNullable(categoryRepository.findByName(addRequest.getCategory().getName()))
                .orElseGet(() ->categoryRepository.save(new Category(addRequest.getCategory().getName())));
        addRequest.setCategory(category);
        return productRepository.save(create(addRequest,category));


    }
    private Product create(AddRequest request, Category category) {
        return new Product(request.getBrand(),request.getName(),request.getDescription(),request.getPrice(),request.getInventory(),category);
    }

    @Override
    public List<Product> FindByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> FindByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> GetAllProdcutsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> GetAllProdcuts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> GetProdcutByname(String name) {
        return productRepository.findProducgtByName(name);
    }

    @Override
    public List<Product> GetAllProdcutsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> GetProdcutByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> GetProdcutByBrandAndName(String brand, String name) {
        return productRepository.findProductByBrand(brand);
    }

    @Override
    public Long CountProductsByCategory(String category) {
        return productRepository.CountProductsByCategoryName(category);

    }

    @Override
    public Long CountProductsByBrand(String brand) {
        return productRepository.CountProductsByBrand(brand);
    }
}
