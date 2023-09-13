package com.udongsari.media.repository;

import com.udongsari.media.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
