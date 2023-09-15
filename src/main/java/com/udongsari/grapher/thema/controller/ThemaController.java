package com.udongsari.grapher.thema.controller;

import com.udongsari.configure.details.PrincipalDetails;
import com.udongsari.grapher.thema.dto.ThemaDto;
import com.udongsari.grapher.thema.service.ThemaServiceImpl;
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
@RequestMapping("/api/v1/user/thema")
public class ThemaController {
    public final ThemaServiceImpl themaServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Long>> createThema(
            Authentication authentication,
            @RequestBody ThemaDto themaDto
    ) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        Long id = themaServiceImpl.createThema(principal.getUser().getGrapherDetail(), themaDto);

        return ResponseEntity.ok(Collections.singletonMap("id", id));
    }
}