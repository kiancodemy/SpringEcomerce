package com.Ecommerce.main.controlers;

import com.Ecommerce.main.services.images.ImageService;
import com.Ecommerce.main.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/product")
@RequiredArgsConstructor
public class ProductController {
    private ProductService productService;
}
