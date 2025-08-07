package com.Ecommerce.main.services.cart;
import com.Ecommerce.main.exception.ProdcutNotfound;
import com.Ecommerce.main.exception.ResourceNotFound;
import com.Ecommerce.main.models.Cart;
import com.Ecommerce.main.models.CartItem;
import com.Ecommerce.main.repository.CartRepository;
import com.Ecommerce.main.repository.CartitemRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class CartService implements CartInter {
    private final CartRepository cartRepository;
    private final CartitemRepository  cartitemRepository;

    @Override
    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElseThrow(()->new RuntimeException("d"));
    }

    @Override
    public void clear(Long id) {
        cartRepository.deleteById(id);

    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
      Cart cart=getCart(id);
      return cart.getTotalPrice();
    }
}