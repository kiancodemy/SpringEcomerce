package com.Ecommerce.main.service.image;
import com.Ecommerce.main.Dto.ImageDto;
import com.Ecommerce.main.exception.ProdcutNotFound;
import com.Ecommerce.main.model.Image;
import com.Ecommerce.main.model.Product;
import com.Ecommerce.main.repository.ImageRepository;
import com.Ecommerce.main.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService implements ImageInterface {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    @Override
    public Image findImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(()->new ProdcutNotFound("image not found"));

    }

    @Override
    public void DeleteImageById(Long id) {
       imageRepository.findById(id).ifPresentOrElse(c->imageRepository.deleteById(id),()->{ throw new ProdcutNotFound("image not found");});

    }

    @Override
    public List<ImageDto> saveImage(List<MultipartFile> file , Long productid) {
        Product product=productRepository.findById(productid).orElseThrow(()->new ProdcutNotFound("product not found"));
        List<ImageDto> images=new ArrayList<>();
        try {
            for (MultipartFile multipartFile : file) {
            Image image=new Image();
            image.setFilename(multipartFile.getOriginalFilename());
            image.setFiletype(multipartFile.getContentType());
            image.setImage(new SerialBlob(multipartFile.getBytes()));
            image.setProduct(product);
            Image saved=imageRepository.save(image);
            String builtIntDownload="/api/v1/images/image/download/";
            String downloadUrl=builtIntDownload+saved.getId();
            saved.setDownloadUrl(downloadUrl);
            imageRepository.save(saved);
            ImageDto imageDto=new ImageDto();
            imageDto.setId(saved.getId());
            imageDto.setFilename(multipartFile.getOriginalFilename());
            imageDto.setDownloadUrl(downloadUrl);
            images.add(imageDto);
            }


        }
        catch (IOException |SQLException e) {
                throw new RuntimeException(e.getMessage());
        }
        return images;

    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        try {
            Image find = findImageById(imageId);

            find.setFilename(file.getOriginalFilename());
            find.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(find);


        } catch (IOException | SQLException e) {
            throw new RuntimeException("Failed to update image");
        }}




}
