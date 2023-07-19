package com.namnd.bookingbe.service.impl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.namnd.bookingbe.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    @Value("${firebase.config.private-key-location}")
    private String privateKeyLocation;
    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
//        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("downloaded private key JSON file path"));
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(privateKeyLocation));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        String fileName = multipartFile.getOriginalFilename();

        String bucketName = "booking-bdcfb.appspot.com";
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

        File file = convertToFile(multipartFile, fileName);
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        String url =  String.format("https://firebasestorage.googleapis.com/v0/b/booking-bdcfb.appspot.com/o/%s?alt=media", URLEncoder.encode(fileName, StandardCharsets.UTF_8));

        System.out.println(url);
        return "File uploaded successfully to Firebase Storage";
    }


    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
