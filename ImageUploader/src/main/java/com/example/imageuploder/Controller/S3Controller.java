package com.example.imageuploder.Controller;

import com.example.imageuploder.exceptions.ImageUploadException;
import com.example.imageuploder.services.ImageUploader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/s3")
@CrossOrigin(origins = "http://localhost:3000")
public class S3Controller {

    private final ImageUploader uploader;

    public S3Controller(ImageUploader uploader) {
        this.uploader = uploader;
    }
    @PostMapping
    public ResponseEntity < ? > uploadImage(@RequestParam MultipartFile file) {

        if (file.isEmpty()) {
            throw new ImageUploadException("File not found");
        }
        return ResponseEntity.ok(uploader.uploadImage(file));
    }

    @GetMapping
    public List<String> getAllFiles(){
        return uploader.allFiles();
    }

    @GetMapping("/{fileName}")
    public String urlByName(@PathVariable("fileName")String fileName){
        return uploader.getImageByName(fileName);
    }
}