package com.Ecommerce.main.services.images;

import com.Ecommerce.main.Dto.ImageDto;
import com.Ecommerce.main.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InterImage {
    Image getImageById(Long id);
    Image getImageByName(String name);
    void deleteImageById(Long id);
    List<ImageDto> saveImage(List<MultipartFile> file, Long prodcutid);
    void UpdateImage(MultipartFile file, Long imageId);
}
