package com.udongsari.grapher.thema.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="THEMA")
public class Thema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "THEMA_ID")
    private Long id;

    @Column(name = "THEMA_NAME")
    private String themaName;

    @OneToMany(mappedBy = "thema")
    private List<Grapher_Thema> grapherThemas;

    @Builder
    public Thema(Long id, String themaName, List<Grapher_Thema> grapherThemas) {
        this.id = id;
        this.themaName = themaName;
        this.grapherThemas = grapherThemas;
    }
}
