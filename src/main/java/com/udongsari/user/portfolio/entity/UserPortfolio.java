package com.udongsari.user.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udongsari.user.post.entity.UserPost;
import lombok.*;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CLIENTS_PORTFOLIO")
public class UserPortfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String imageTitle;

    @Column(name = "IMG_PATH")
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserPost.class)
    @JoinColumn(name = "POST_ID")
    private UserPost userPost;

    @Builder
    public UserPortfolio(Long id, String imageTitle, String imagePath, UserPost userPost) {
        this.id = id;
        this.imageTitle = imageTitle;
        this.imagePath = imagePath;
        this.userPost = userPost;
    }
}
