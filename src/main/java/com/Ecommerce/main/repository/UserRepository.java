package com.Ecommerce.main.repository;

import com.Ecommerce.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
