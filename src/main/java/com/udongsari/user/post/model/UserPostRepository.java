package com.udongsari.user.post.model;

import com.udongsari.user.post.entity.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostRepository extends JpaRepository<UserPost, Long> {
}
