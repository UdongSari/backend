package com.udongsari.grapher.portfolio.controller;

import com.udongsari.configure.details.PrincipalDetails;
import com.udongsari.grapher.portfolio.dto.PortfolioDto;
import com.udongsari.grapher.portfolio.service.PortfolioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/portfolio")
public class PortfolioController {
    public final PortfolioServiceImpl portfolioServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Long>> createPortfolio(
            Authentication authentication,
            @RequestBody PortfolioDto portfolioDto
    ) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        Long id = portfolioServiceImpl.createPortfolio(principal.getUser().getGrapherDetail(), portfolioDto);

        return ResponseEntity.ok(Collections.singletonMap("id", id));
    }
}