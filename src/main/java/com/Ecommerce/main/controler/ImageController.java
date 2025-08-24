package com.Ecommerce.main.controler;
import com.Ecommerce.main.Dto.ImageDto;
import com.Ecommerce.main.model.Image;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImage(@RequestParam List<MultipartFile> file,@RequestParam Long id){
        try{
            List<ImageDto> images= imageService.saveImage(file,id);
            return ResponseEntity.ok(new ApiResponse("sucessfulley saved",images));
        }

        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));

        }}

    @GetMapping("/image/download/{id}")
    public ResponseEntity<Resource> downloadImage(@PathVariable("id") Long id){
        Image image = imageService.findImageById(id);
        try{
           byte[] imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());

           ByteArrayResource resource = new ByteArrayResource(imageBytes);

           return ResponseEntity.ok()
                   .header("Content-Disposition", "attachment; filename=\"" + image.getFilename() + "\"")
                   .header("Content-Type", image.getFiletype())
                   .body(resource);
        }
        catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable("id") Long id, @RequestBody MultipartFile file){

        try{
            imageService.updateImage(file,id);
            return  ResponseEntity.ok(new ApiResponse("sucessfulley saved",null));

        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));

        }

    }

    @DeleteMapping("/deleteImage/{id}")
    public ResponseEntity<ApiResponse> DeleteId(@PathVariable("id") Long id ){
        try {
            imageService.DeleteImageById(id);
            return ResponseEntity.ok(new ApiResponse("sucessfulley deleted",null));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/ImageById/{id}")
    public ResponseEntity<ApiResponse> findImageById(@PathVariable("id") Long id){
        try {
            Image findImage=imageService.findImageById(id);
            return ResponseEntity.ok(new ApiResponse("sucessfulley",findImage));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
