package com.br.femmcode.femmcode.controllers;

import com.br.femmcode.femmcode.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin(origins = "http://localhost:5173")
public class CloudinaryController {
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            String result = cloudinaryService.uploadFile(file);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro no upload: " + e.getMessage());
        }
    }
}
