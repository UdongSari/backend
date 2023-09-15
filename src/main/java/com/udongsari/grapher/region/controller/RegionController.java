package com.udongsari.grapher.region.controller;

import com.udongsari.configure.details.PrincipalDetails;
import com.udongsari.grapher.region.dto.RegionDto;
import com.udongsari.grapher.region.service.RegionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/region")
public class RegionController {
    public final RegionServiceImpl regionServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Long>> createRegion(
            Authentication authentication,
            @RequestBody RegionDto regionDto
    ) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        System.out.println(principal.getUser().getUsername());
        System.out.println(principal.getUser().getGrapherDetail().getSnsAddress());
        Long id = regionServiceImpl.createRegion(principal.getUser().getGrapherDetail(), regionDto);

        return ResponseEntity.ok(Collections.singletonMap("id", id));
    }
}
