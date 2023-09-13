package com.udongsari.grapher.portfolio.entity;

import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import com.udongsari.grapher.region.entity.Region;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PORTFOLIO")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String imageTitle;

    @Column(name = "IMG_PATH")
    private String imagePath;

    // Di
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = GrapherDetail.class)
    @JoinColumn(name = "GRAPHER_ID")
    private GrapherDetail grapherDetail;
}
