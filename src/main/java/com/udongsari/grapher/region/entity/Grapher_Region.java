package com.udongsari.grapher.region.entity;

import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRAPHER_REGION")
public class Grapher_Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    // Di
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = GrapherDetail.class)
    @JoinColumn(name = "GRAPHER_ID")
    private GrapherDetail grapherDetail;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Region.class)
    @JoinColumn(name = "REGION_ID")
    private Region region;
}
