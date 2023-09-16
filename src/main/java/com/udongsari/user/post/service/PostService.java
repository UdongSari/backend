package com.udongsari.user.post.service;

import com.udongsari.account.entity.Account;
import com.udongsari.user.post.dto.UserPostDto;
import com.udongsari.user.post.entity.UserPost;

import java.util.List;

public interface PostService {
    Long createPost(Account account, UserPostDto userPostDto);
    UserPost showMyPost(Account account);
    List<UserPostDto> searchUsersPost(String si, String gu, String dong);
    UserPostDto showUserPost(Long post_id);
}
