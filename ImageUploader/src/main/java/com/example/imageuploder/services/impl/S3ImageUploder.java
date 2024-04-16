package com.example.imageuploder.services.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.example.imageuploder.exceptions.ImageUploadException;
import com.example.imageuploder.services.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class S3ImageUploder implements ImageUploader {
    @Autowired
    private AmazonS3 client;

    @Value("${app.s3.bucket}")
    private String bucketName;

    @Override
    public String uploadImage(MultipartFile image) {

        if (image == null) {
            throw new ImageUploadException("Image is null,Please upload the image");
        }

        String actualFileName = image.getOriginalFilename();

        String fileName = UUID.randomUUID() + (actualFileName != null ? actualFileName.substring(actualFileName.lastIndexOf(".")) : null);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());

        try {
            PutObjectResult putObjectRequest = client.putObject(new PutObjectRequest(bucketName, fileName, image.getInputStream(), metadata));
            return this.preSignedURL(fileName);
        } catch (IOException e) {
            throw new ImageUploadException("Error in Uploading Image" + e.getMessage());
        }
    }

    @Override
    public List < String > allFiles() {

        ListObjectsV2Result listObjectsV2Result = client.listObjectsV2(new ListObjectsV2Request()
                .withBucketName(bucketName));

        List < S3ObjectSummary > objectSummaries = listObjectsV2Result.getObjectSummaries();

        return objectSummaries.stream()
                .map(item -> this.preSignedURL(item.getKey()))
                .toList();
    }

    @Override
    public String preSignedURL(String fileName) {

        Date expirateDate = new Date();
        int hour = 2;
        long time = expirateDate.getTime();

        time = time + hour * 60 * 60 * 1000;

        expirateDate.setTime(time);
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, fileName)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expirateDate);

        URL url = client.generatePresignedUrl(generatePresignedUrlRequest);

        return url.toString();
    }

    @Override
    public String getImageByName(String fileName) {
        S3Object object = client.getObject(bucketName, fileName);
        String key = object.getKey();
        return preSignedURL(key);
    }
}