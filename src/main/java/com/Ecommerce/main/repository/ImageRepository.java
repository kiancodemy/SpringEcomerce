package com.Ecommerce.main.repository;

import com.Ecommerce.main.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {

    List<Image> findByProductId(Long productId);
}
