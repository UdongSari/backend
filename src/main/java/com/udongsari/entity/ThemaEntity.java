package com.udongsari.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "THEMA")
public class ThemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String themaName;

    @JsonIgnore
    @OneToMany(
            targetEntity = User_Thema.class,
            mappedBy = "thema",
            fetch = FetchType.LAZY
    )
    private List<User_Thema> user_themaList;

    @Builder
    public ThemaEntity(Long id, String themaName, List<User_Thema> user_themaList) {
        this.id = id;
        this.themaName = themaName;
        this.user_themaList = user_themaList;
    }
}
