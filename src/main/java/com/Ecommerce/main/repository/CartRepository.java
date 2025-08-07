package com.Ecommerce.main.repository;

import com.Ecommerce.main.models.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
