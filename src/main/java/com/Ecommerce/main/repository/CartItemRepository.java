package com.Ecommerce.main.repository;

import com.Ecommerce.main.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems,Long>{


}
