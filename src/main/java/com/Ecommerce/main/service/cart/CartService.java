package com.Ecommerce.main.service.cart;
import com.Ecommerce.main.exception.CartNotFound;
import com.Ecommerce.main.model.Cart;
import com.Ecommerce.main.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements CartInterface {
    private final CartRepository cartRepository;



    @Override
    public Cart getCartById(Long id) {
       Cart findCart= cartRepository.findById(id).orElseThrow(()-> new CartNotFound("cart not found"));
       findCart.UpdateTotalAmount();
       return cartRepository.save(findCart);
    }

    @Override
    public void clearCart(Long cartId) {
        cartRepository.deleteById(cartId);

    }

    @Override
    public BigDecimal getTotalPrice(Long cartId) {
        Cart find=getCartById(cartId);
        return find.getTotalAmount();

    }
}
