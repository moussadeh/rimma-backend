package com.moussa.rimma_backend.controllers;

import com.moussa.rimma_backend.services.CloudinaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Images", description = "Gestion de l’upload des images vers Cloudinary")
@RestController
@RequestMapping("/rimma/api/images")
public class ImageController {

    private final CloudinaryService cloudinaryService;

    public ImageController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @Operation(
            summary = "Uploader des images",
            description = "Permet d’uploader une ou plusieurs images vers Cloudinary. Retourne la liste des URLs générées après upload."
    )
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<List<String>> uploadImages(@RequestPart("files") List<MultipartFile> files) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = cloudinaryService.upload(file);
            urls.add(url);
        }
        return ResponseEntity.ok(urls);
    }
}
