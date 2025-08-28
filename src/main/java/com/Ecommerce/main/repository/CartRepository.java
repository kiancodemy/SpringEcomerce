package com.Ecommerce.main.repository;

import com.Ecommerce.main.model.Cart;
import com.Ecommerce.main.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long userid);


}
