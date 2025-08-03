package com.Ecommerce.main.controlers;

import com.Ecommerce.main.Dto.ImageDto;
import com.Ecommerce.main.models.Image;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.services.images.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/image")
@RequiredArgsConstructor
public class ImageController {
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId) {
        try {
            List<ImageDto> all = imageService.saveImage(files, productId);
            return ResponseEntity.ok(new ApiResponse("sucessful save", all));
        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to save", e.getMessage()));

        }
    }

    @GetMapping("/download/{imageId}")
    public ResponseEntity<Resource> downlaodimage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        Blob blob = image.getImage();
        ByteArrayResource resource = new ByteArrayResource(blob.getBytes(1, (int) blob.length()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"")
                .contentType(MediaType.parseMediaType(image.getFiletype()))
                .body(resource);
    }

    @PutMapping("/updated/{imageId}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long imageId, @RequestBody MultipartFile image) {
        try {
            imageService.UpdateImage(image , imageId);
            return ResponseEntity.ok(new ApiResponse("sucessfully  updateted ", null));


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed to update", e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long imageId) {
        try {
            imageService.deleteImageById(imageId);
            return ResponseEntity.ok(new ApiResponse("sucessfully deleted ", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse("failed to deleted ", e.getMessage()));
        }
    }



}
