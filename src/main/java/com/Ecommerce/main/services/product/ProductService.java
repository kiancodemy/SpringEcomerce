package com.Ecommerce.main.services.product;
import com.Ecommerce.main.Dto.ImageDto;
import com.Ecommerce.main.Dto.ProductDto;
import com.Ecommerce.main.exception.ProdcutNotfound;
import com.Ecommerce.main.models.Category;
import com.Ecommerce.main.models.Product;
import com.Ecommerce.main.repository.ImageRepository;
import com.Ecommerce.main.repository.ProductRepository;
import com.Ecommerce.main.repository.CategoryRepository;
import com.Ecommerce.main.request.AddRequest;
import com.Ecommerce.main.request.UpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements InterProduct{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

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
         productRepository.deleteProductByName(name);

    }

    @Override
    public void UpdateProdcutbyid(UpdateRequest product, Long id) {
        productRepository.findById(id).ifPresentOrElse(
                existingProduct -> {
                    Product updated = existing(existingProduct, product); // merge logic
                    productRepository.save(updated);
                },
                () -> {
                    throw new ProdcutNotfound("product not found");
                }
        );

    }

    private Product existing(Product existing,UpdateRequest request){
        existing.setName(request.getName());
        existing.setBrand(request.getBrand());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setInventory(request.getInventory());
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> categoryRepository.save(new Category(request.getCategory().getName())));
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
        return productRepository.findProductByName(name);
    }



    @Override
    public List<Product> GetAllProdcutsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> GetAllProdcuts() {
        return productRepository.findAll();
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
    public Long CountProductsByBrandandname(String brand,String name) {
        return productRepository.countProductByBrandAndName(brand,name);
    }

    @Override
    public List<ProductDto> convertToDtoList(List<Product> products){
        return products.stream().map(this::convertToDt).toList();
    }


    @Override
    public ProductDto convertToDt(Product product){
        ProductDto productdtop=modelMapper.map(product,ProductDto.class);
        List<ImageDto> imagedto=imageRepository.findByProductId(product.getId()).stream().map(image->modelMapper.map(image,ImageDto.class)).collect(Collectors.toList());
        productdtop.setImages(imagedto);
        return productdtop;
    }

}
