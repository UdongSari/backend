package com.udongsari.user.user_region.entity;

import com.udongsari.grapher.region.entity.Region;
import com.udongsari.user.post.entity.UserPost;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CLIENT_REGION")
public class User_Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserPost.class)
    @JoinColumn(name = "POST_ID")
    private UserPost userPost;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Region.class)
    @JoinColumn(name = "REGION_ID")
    private Region region;

    @Builder
    public User_Region(Long id, UserPost userPost, Region region) {
        this.id = id;
        this.userPost = userPost;
        this.region = region;
    }
}
