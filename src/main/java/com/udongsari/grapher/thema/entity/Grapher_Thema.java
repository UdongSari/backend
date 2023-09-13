package com.udongsari.grapher.thema.entity;

import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRAPHER_THEMA")
public class Grapher_Thema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    // Di
    @ManyToOne
    @JoinColumn(name = "GRAPHER_ID")
    private GrapherDetail grapherDetail;

    @ManyToOne
    @JoinColumn(name = "THEMA_ID")
    private Thema thema;
}
