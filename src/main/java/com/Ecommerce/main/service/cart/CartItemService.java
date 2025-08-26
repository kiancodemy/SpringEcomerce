package com.Ecommerce.main.service.cart;
import com.Ecommerce.main.exception.ProdcutNotFound;
import com.Ecommerce.main.model.Cart;
import com.Ecommerce.main.model.CartItems;
import com.Ecommerce.main.model.Product;
import com.Ecommerce.main.repository.CartItemRepository;
import com.Ecommerce.main.repository.CartRepository;
import com.Ecommerce.main.repository.CategoryRepository;
import com.Ecommerce.main.repository.ProductRepository;
import com.Ecommerce.main.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService implements CarItemInterface  {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartService cartService;


    @Override
    public void addCartItem(Long cartId, Long productId, int quantity) {
        Cart cart=cartService.getCartById(cartId);
        Product product=productService.getProductById(productId);
        CartItems findSimilarProduct=cart.getCartItems().stream().filter(item->item.getProduct().equals(product)).findFirst().orElse(null);
       if(findSimilarProduct==null){
           CartItems newCartItem= new CartItems();
           newCartItem.setQuantity(quantity);
           newCartItem.setProduct(product);
           newCartItem.setUnitPrice(product.getPrice());
           newCartItem.setTotalPrice();
           cart.addItem(newCartItem);
       }
       else{
           findSimilarProduct.setQuantity(findSimilarProduct.getQuantity()+quantity);
           findSimilarProduct.setUnitPrice(product.getPrice());
           findSimilarProduct.setTotalPrice();
       }

       cart.UpdateTotalAmount();
       cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart=cartService.getCartById(cartId);
        Product product=productService.getProductById(productId);
        CartItems findCartItem=cart.getCartItems().stream().filter(item->item.getProduct().equals(product)).findFirst().orElseThrow(()->new ProdcutNotFound("product not found"));
        cart.removeItem(findCartItem);
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart=cartService.getCartById(cartId);

        cart.getCartItems().stream().filter(item->item.getProduct().getId().equals(productId)
        ).findFirst().ifPresent(first->{first.setQuantity(first.getQuantity());first.setUnitPrice(first.getProduct().getPrice());first.setTotalPrice();});
        cart.UpdateTotalAmount();
        cartRepository.save(cart);


    }
}
