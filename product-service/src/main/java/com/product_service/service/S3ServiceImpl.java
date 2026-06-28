package com.product_service.service;

import com.product_service.entity.Brand;
import com.product_service.entity.Image;
import com.product_service.repository.BrandRepository;
import com.product_service.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;

@Service
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    private ImageRepository imageRepository;

    private BrandRepository brandRepository;

    @Value("${bucket-name}")
    private String bucketName;

    @Value("${region}")
    private String region;

    public S3ServiceImpl(S3Client s3Client, ImageRepository imageRepository, BrandRepository brandRepository) {
        this.s3Client = s3Client;
        this.imageRepository = imageRepository;
        this.brandRepository = brandRepository;
    }
    public String uploadImage(MultipartFile file, int brandId) throws IOException {

        String fileName = System.currentTimeMillis() + "_"
                + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        PutObjectResponse putObjectResponse = s3Client.putObject(request,
                RequestBody.fromBytes(file.getBytes())
        );

        String url = "https://" + bucketName + ".s3." + region + ".amazonaws.com/"
                + fileName;
        //Save data inside Image
        Brand brand = brandRepository.findById(brandId).get();
        Image  image = new Image();
        image.setBrand(brand);
        image.setUrl(url);


        imageRepository.save(image);
        return url;
    }
}
