package com.udongsari.grapher.grapherDetail.controller;

import com.udongsari.configure.details.PrincipalDetails;
import com.udongsari.exception.duplicateGrapherException;
import com.udongsari.grapher.grapherDetail.dto.GrapherDetailDto;
import com.udongsari.grapher.grapherDetail.service.GrapherDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/grapher")
public class GrapherDetailController {
    public final GrapherDetailServiceImpl grapherDetailServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Long>> createGrapher(
            Authentication authentication,
            @RequestBody GrapherDetailDto grapherDetailDto
    ) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        Long id = grapherDetailServiceImpl.createGrapher(principal.getUser(), grapherDetailDto);

        return ResponseEntity.ok(Collections.singletonMap("id", id));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<GrapherDetailDto> readGrapher(@PathVariable("id") Long id) {
        GrapherDetailDto grapherDetailDto = grapherDetailServiceImpl.readGrapher(id);

        return ResponseEntity.ok(grapherDetailDto);
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<GrapherDetailDto>> readAllGrapher() {
        List<GrapherDetailDto> grapherDetailDtos = grapherDetailServiceImpl.readAllGrapher();

        return ResponseEntity.ok(grapherDetailDtos);
    }

}