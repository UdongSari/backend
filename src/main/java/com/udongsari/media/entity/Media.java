package com.udongsari.media.entity;

import com.udongsari.media.dto.MediaDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "USER_ACCOUNT")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Builder
    public Media(Long id, String filePath) {
        this.id = id;
        this.filePath = filePath;
    }

    public MediaDto toDto() {
        return MediaDto.builder()
                .id(id)
                .filePath(filePath)
                .build();
    }
}
