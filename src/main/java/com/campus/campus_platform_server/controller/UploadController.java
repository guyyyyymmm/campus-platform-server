package com.campus.campus_platform_server.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "http://localhost:5173")
public class UploadController {

    @PostMapping
    public Map<String, String> upload(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (file.getContentType() == null
                || !file.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("只能上传图片文件");
        }

        if (file.getSize() > 5 * 1024 * 1024) {
            throw new IllegalArgumentException("图片大小不能超过 5MB");
        }

        Path uploadDir = Paths.get("uploads");
        Files.createDirectories(uploadDir);

        String originalName = file.getOriginalFilename();
        String extension = "";

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(
                    originalName.lastIndexOf(".")
            );
        }

        String fileName = UUID.randomUUID() + extension;
        Path filePath = uploadDir.resolve(fileName);

        file.transferTo(filePath);

        return Map.of(
                "url",
                "http://localhost:8080/uploads/" + fileName
        );
    }
}