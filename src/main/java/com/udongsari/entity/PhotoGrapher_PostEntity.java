package com.udongsari.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PHOTOGRAPHER_POST")
public class PhotoGrapher_PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String SNS_link;
    private String price;


    @JsonIgnore
    @OneToOne(
            targetEntity = Account.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "USER_ID")
    private Account Account;

    @JsonIgnore
    @OneToMany(
            targetEntity = PhotosEntity.class,
            mappedBy = "photoGrapherPost",
            fetch = FetchType.LAZY
    )
    private List<PhotosEntity> photosEntityList;

    @Builder
    public PhotoGrapher_PostEntity(Long id, String SNS_link, String price, com.udongsari.entity.Account account, List<PhotosEntity> photosEntityList) {
        this.id = id;
        this.SNS_link = SNS_link;
        this.price = price;
        Account = account;
        this.photosEntityList = photosEntityList;
    }
}
