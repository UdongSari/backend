package com.udongsari.grapher.region.entity;

import com.udongsari.user.user_region.entity.User_Region;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="REGION")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_ID")
    private Long id;

    // Details
    @Column(name = "SI")
    private String si;

    @Column(name = "GU")
    private String gu;

    @Column(name = "DONG")
    private String dong;

    // Di
    @OneToMany(mappedBy = "region")
    private List<Grapher_Region> grapherRegions;

    @OneToMany(mappedBy = "region")
    private List<User_Region> userRegions;

    @Builder
    public Region(Long id, String si, String gu, String dong, List<Grapher_Region> grapherRegions) {
        this.id = id;
        this.si = si;
        this.gu = gu;
        this.dong = dong;
        this.grapherRegions = grapherRegions;
    }
}
