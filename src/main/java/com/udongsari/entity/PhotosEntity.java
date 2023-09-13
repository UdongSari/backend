package com.udongsari.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PHOTOS")
public class PhotosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalName;
    private String resourcePath;

    @JsonIgnore
    @ManyToOne(
            targetEntity = PhotoGrapher_PostEntity.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "PhotoGrapherPOST_ID")
    private PhotoGrapher_PostEntity photoGrapherPost;

    @Builder
    public PhotosEntity(Long id, String originalName, String resourcePath, PhotoGrapher_PostEntity photoGrapher_Post) {
        this.id = id;
        this.originalName = originalName;
        this.resourcePath = resourcePath;
        this.photoGrapherPost = photoGrapher_Post;
    }
}
