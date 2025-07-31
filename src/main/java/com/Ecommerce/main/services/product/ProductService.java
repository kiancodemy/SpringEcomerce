package com.Ecommerce.main.services.product;
import com.Ecommerce.main.exception.ProdcutNotfound;
import com.Ecommerce.main.models.Product;
import com.Ecommerce.main.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements InterProduct{
    private final ProductRepository productRepository;

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
    public void UpdateProdcutbyid(Product prodcut, Long id) {

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
