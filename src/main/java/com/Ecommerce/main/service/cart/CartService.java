package com.Ecommerce.main.service.cart;
import com.Ecommerce.main.exception.CartNotFound;
import com.Ecommerce.main.model.Cart;
import com.Ecommerce.main.model.CartItems;
import com.Ecommerce.main.repository.CartItemRepository;
import com.Ecommerce.main.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CartService implements CartInterface {
    private final CartRepository cartRepository;
    private final AtomicLong counter = new AtomicLong(0);
    private final CartItemRepository cartItemRepository;


    @Override
    public Cart getCartById(Long id) {
       Cart findCart= cartRepository.findById(id).orElseThrow(()-> new CartNotFound("cart not found"));
       findCart.UpdateTotalAmount();
       return cartRepository.save(findCart);
    }

    @Override
    @Transactional
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFound("Cart not found"));
        List<CartItems> items = cartItemRepository.findByCartId(cartId);
        cartItemRepository.deleteAll(items);
        cart.getCartItems().clear();
        cart.setTotalAmount(BigDecimal.ZERO);
        cartRepository.save(cart);



    }

    @Override
    public BigDecimal getTotalPrice(Long cartId) {
        Cart find=getCartById(cartId);
        return find.getTotalAmount();

    }
    @Override
    public Long addRandomId(){
        Cart newCart = new Cart();
        return cartRepository.save(newCart).getId();

    }
}
