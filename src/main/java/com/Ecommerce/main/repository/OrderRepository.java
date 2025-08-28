package com.Ecommerce.main.repository;

import com.Ecommerce.main.model.Order;
import com.Ecommerce.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> user(User user);

    List<Order> findByUserId(Long userid);

}
