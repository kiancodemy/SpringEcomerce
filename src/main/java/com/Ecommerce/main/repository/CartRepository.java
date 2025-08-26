package com.Ecommerce.main.repository;

import com.Ecommerce.main.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
