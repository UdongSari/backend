package com.udongsari.media.service;

import com.udongsari.media.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService{
    private final MediaRepository mediaRepository;

    @Value("${spring.baseUrl}")
    private String baseUrl;

    @Override
    public String generatePath() {
        return null;
    }
}
