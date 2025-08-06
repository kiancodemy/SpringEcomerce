package com.Ecommerce.main.services.images;
import com.Ecommerce.main.Dto.ImageDto;
import com.Ecommerce.main.exception.ResourceNotFound;
import com.Ecommerce.main.models.Image;
import com.Ecommerce.main.models.Product;
import com.Ecommerce.main.repository.ImageRepository;
import com.Ecommerce.main.services.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService implements InterImage {
    private final ImageRepository imageRepository;
    private final ProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(()->new ResourceNotFound("iamge not found by id "));

    }

    @Override
    public Image getImageByName(String name) {
        return null;
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(c->imageRepository.deleteById(id),()->{throw new ResourceNotFound("not found by id ");});

    }

    @Override
    @Transactional
    public List<ImageDto> saveImage(List<MultipartFile> file, Long prodcutid) {
       try{
           Product product =productService.GetProdcutbyid(prodcutid);
           List<ImageDto> all=new ArrayList<>();
           for(MultipartFile files: file){
               Image image = new Image();
               if (files.getSize() > 5 * 1024 * 1024) {
                   throw new IllegalArgumentException("File " + files.getOriginalFilename() + " exceeds 5MB limit.");
               }


               image.setFilename(files.getOriginalFilename());
               image.setFiletype(files.getContentType());
               image.setImage(new SerialBlob(files.getBytes()));
               image.setProduct(product);
               String dowanloadUrl="/images/image/download/";
               Image Newimage=imageRepository.save(image);
               Newimage.setDownloadurl(dowanloadUrl+Newimage.getId());
               imageRepository.save(Newimage);

               ImageDto savedImageDto=new ImageDto();
               savedImageDto.setId(Newimage.getId());
               savedImageDto.setFilename(Newimage.getFilename());
               savedImageDto.setDownloadurl(Newimage.getDownloadurl());
               all.add(savedImageDto);}
           return all;
       }
       catch (Exception e){
           throw new RuntimeException("error");
       }
    }

    @Override
    public void UpdateImage(MultipartFile file, Long imageId) {
        try{
            Image image=getImageById(imageId);
            image.setFilename(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);}
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
