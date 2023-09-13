package com.udongsari.grapher.thema.entity;

import lombok.AccessLevel;
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
    private String themeName;

    @OneToMany(mappedBy = "thema")
    private List<Grapher_Thema> grapherThemas;
}
