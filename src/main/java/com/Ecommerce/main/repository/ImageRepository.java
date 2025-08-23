package com.Ecommerce.main.repository;
import com.Ecommerce.main.model.Category;
import com.Ecommerce.main.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
