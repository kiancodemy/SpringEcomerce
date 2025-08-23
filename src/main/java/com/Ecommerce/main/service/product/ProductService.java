package com.Ecommerce.main.service.product;
import com.Ecommerce.main.exception.ProdcutNotFound;
import com.Ecommerce.main.model.Category;
import com.Ecommerce.main.model.Product;
import com.Ecommerce.main.repository.CategoryRepository;
import com.Ecommerce.main.repository.ProductRepository;
import com.Ecommerce.main.request.AddProduct;
import com.Ecommerce.main.request.UpdateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService implements ProductInterface {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public Product addProduct(AddProduct product) {

        Product newOne=createProduct(product);
        Category find= Optional.ofNullable(categoryRepository.findByName(product.getCategoryName())).orElseGet(()->{Category newCategory=new Category(product.getCategoryName());return categoryRepository.save(newCategory);});
        newOne.setCategory(find);
        return productRepository.save(newOne);

    }

    public Product createProduct(AddProduct request) {
        return new Product(request.getName(),request.getDescription(),request.getBrand(),request.getPrice(),request.getInventory());

    }

    @Override
    public Product updateProduct(UpdateProduct product, Long id) {
        return productRepository.findById(id).map(c->UpdateExisting(c,product)).map(productRepository::save).orElseThrow(()->new ProdcutNotFound("not found"));


    }
    private Product UpdateExisting(Product find ,UpdateProduct product){
        find.setName(product.getName());
        find.setDescription(product.getDescription());
        find.setBrand(product.getBrand());
        find.setPrice(product.getPrice());
        find.setInventory(product.getInventory());
        Category findCategory=Optional.ofNullable(categoryRepository.findByName(product.getCategory())).orElseGet(()->{Category newCategory=new Category(product.getCategory());return categoryRepository.save(newCategory);});
        find.setCategory(findCategory);
        return find;

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
    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.findProductByCategoryName(category);
    }

    @Override
    public List<Product> getAllProductByBrand(String brand) {
        return productRepository.findProductByBrand(brand);
    }

    @Override
    public List<Product> getAllProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findProductByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return productRepository.findProductByBrandAndName(brand,name);
    }

    @Override
    public Long CountProductByBrandAndName(String brand, String name) {
        return productRepository.countProductByBrandAndName(brand,name);
    }
}
