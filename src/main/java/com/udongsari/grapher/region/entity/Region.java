package com.udongsari.grapher.region.entity;

import lombok.*;

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

    @Builder
    public Region(Long id, String si, String gu, String dong, List<Grapher_Region> grapherRegions) {
        this.id = id;
        this.si = si;
        this.gu = gu;
        this.dong = dong;
        this.grapherRegions = grapherRegions;
    }
}
