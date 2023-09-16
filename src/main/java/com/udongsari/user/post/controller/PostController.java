package com.udongsari.user.post.controller;

import com.udongsari.configure.details.PrincipalDetails;
import com.udongsari.grapher.grapherDetail.dto.GrapherDetailDto;
import com.udongsari.grapher.region.dto.RegionDto;
import com.udongsari.user.post.dto.UserPostDto;
import com.udongsari.user.post.entity.UserPost;
import com.udongsari.user.post.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user/post")
public class PostController {
    private final PostServiceImpl postServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Long>> createUserPost(
            Authentication authentication,
            @RequestBody UserPostDto userPostDto
    ){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        Long id = postServiceImpl.createPost(principal.getUser(), userPostDto);

        return ResponseEntity.ok(Collections.singletonMap("id", id));
    }

    @GetMapping("/myPostRead")
    public ResponseEntity<UserPost> showMyPostDetails(
            Authentication authentication
    ){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        UserPost userPost = postServiceImpl.showMyPost(principal.getUser());
        return ResponseEntity.ok(userPost);
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserPostDto>> searchPostBySiAndGuAndDong(@RequestBody RegionDto regionDto) {
        List<UserPostDto> userPostDtos = postServiceImpl.searchUsersPost(regionDto.getSi(), regionDto.getGu(), regionDto.getDong());
        return ResponseEntity.ok(userPostDtos);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserPostDto> readGrapher(@PathVariable("id") Long id) {
        UserPostDto userPostDto = postServiceImpl.showUserPost(id);

        return ResponseEntity.ok(userPostDto);
    }
}
