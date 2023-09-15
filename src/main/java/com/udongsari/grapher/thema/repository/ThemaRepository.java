package com.udongsari.grapher.thema.repository;

import com.udongsari.grapher.thema.entity.Thema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemaRepository extends JpaRepository<Thema, Long> {
    Optional<Thema> findByThemaName(String themaName);
}
