package com.Ecommerce.main.repository;

import com.Ecommerce.main.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartitemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
