package com.namnd.bookingbe.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FirebaseStorageService {

    public String uploadFile(MultipartFile file) throws IOException;
}
