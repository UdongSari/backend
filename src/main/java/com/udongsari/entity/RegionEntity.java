package com.udongsari.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="REGION")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String si;
    private String gu;
    private String dong;

    @JsonIgnore
    @OneToMany(
            targetEntity = User_Region.class,
            mappedBy = "region",
            fetch = FetchType.LAZY
    )
    private List<User_Region> user_regionList;

    @Builder
    public RegionEntity(Long id, String si, String gu, String dong, List<User_Region> user_regionList) {
        this.id = id;
        this.si = si;
        this.gu = gu;
        this.dong = dong;
        this.user_regionList = user_regionList;
    }
}
