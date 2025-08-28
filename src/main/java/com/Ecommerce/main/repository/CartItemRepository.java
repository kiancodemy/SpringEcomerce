package com.Ecommerce.main.repository;

import com.Ecommerce.main.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItems,Long>{


    List<CartItems> findByCartId(Long cartId);
}
