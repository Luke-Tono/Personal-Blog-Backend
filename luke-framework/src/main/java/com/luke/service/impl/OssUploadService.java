package com.luke.service.impl;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.luke.domain.ResponseResult;
import com.luke.enums.AppHttpCodeEnum;
import com.luke.exception.SystemException;
import com.luke.service.UploadService;
import com.luke.utils.PathUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


import java.io.IOException;


@Service
public class OssUploadService implements UploadService {
    @SneakyThrows
    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        //TODO 判断文件类型或大小

        String originalFilename = img.getOriginalFilename();
        if(!originalFilename.endsWith(".png") &&  !originalFilename.endsWith(".jpg")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }



        //如果判断通过 上传文件到OSS

        String filePath = PathUtils.generateFilePath(originalFilename);
        String url = uploadOss(img, filePath);

        return ResponseResult.okResult(url);
    }



    @Value("${oss.bucket}")
    private String bucket;
    @Value("${oss.accessKey}")
    private String accessKey;
    @Value("${oss.secretKey}")
    private String secretKey;
    @Value("${oss.region}")
    private String region;


    private String uploadOss(MultipartFile img, String filePath) throws IOException {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        String objectKey = "uploads/" +filePath;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(img.getContentType());
        metadata.setContentLength(img.getSize());

        PutObjectRequest request = new PutObjectRequest(bucket, objectKey, img.getInputStream(), metadata);
        s3Client.putObject(request);

        return "https://" + bucket + ".s3.amazonaws.com/" + objectKey;
    }
}

