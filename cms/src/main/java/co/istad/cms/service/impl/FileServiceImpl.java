package co.istad.cms.service.impl;

import co.istad.cms.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${file.server-path}")
    private String fileServerPath;

    @Override
    public Map<String, Object> uploadSingle(MultipartFile file) {

        String fileName = UUID.randomUUID().toString();
        int lastIndexOfDot = file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastIndexOfDot);
        fileName += extension;

        System.out.println(extension);
        System.out.println(fileName);

        // Create path object
        Path path = Paths.get(fileServerPath + fileName);

        // Copy file to server path
        try {
            Files.copy(file.getInputStream(), path);
            Map<String, Object> result = new HashMap<>();
            result.put("isUploaded", true);
            result.put("fileName", fileName);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
