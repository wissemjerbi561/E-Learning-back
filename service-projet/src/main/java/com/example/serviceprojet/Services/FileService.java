package com.example.serviceprojet.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
@Service
public class FileService {
    @Autowired
    private ResourceLoader resourceLoader;

    public void uploadFile(MultipartFile file) throws IOException {

        File file1 = new File(
                resourceLoader.getResource("classpath:store/").getFile() + "/" + file.getOriginalFilename());
        if (file1.createNewFile()) {
            System.out.println("File is created!" + file1.getAbsolutePath());

        } else {
            System.out.println("File already exists.");
        }

        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file1));
        stream.write(file.getBytes());
        stream.close();
    }
}
