package com.Ecommerce.main.service.image;
import com.Ecommerce.main.Dto.ImageDto;
import com.Ecommerce.main.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageInterface {

   Image findImageById(Long id);
   void DeleteImageById(Long id);
   List<ImageDto> saveImage(List<MultipartFile> file, Long productid);
   void updateImage(MultipartFile file,Long imageId);



}
