package com.udongsari.media.controller;

import com.udongsari.media.service.MediaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/photographer")
public class MediaController {
    private final MediaServiceImpl mediaServiceImpl;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Long>> uploading(
            @RequestParam MultipartFile multipartFile
    ) {
        return null;
    }

}
