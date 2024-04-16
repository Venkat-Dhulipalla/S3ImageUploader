package com.example.imageuploder.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUploader {

    String uploadImage(MultipartFile image);

    List<String> allFiles();

    String preSignedURL(String fileName);

    String getImageByName(String fileName);



}
