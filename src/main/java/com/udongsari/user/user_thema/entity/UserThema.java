package com.udongsari.user.user_thema.entity;


import com.udongsari.grapher.thema.entity.Thema;
import com.udongsari.user.post.entity.UserPost;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CLIENT_THEMA")
public class UserThema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserPost.class)
    @JoinColumn(name = "POST_ID")
    private UserPost userPost;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Thema.class)
    @JoinColumn(name = "THEMA_ID")
    private Thema thema;

    @Builder
    public UserThema(Long id, UserPost userPost, Thema thema) {
        this.id = id;
        this.userPost = userPost;
        this.thema = thema;
    }
}
