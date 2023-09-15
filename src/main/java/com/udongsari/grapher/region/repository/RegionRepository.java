package com.udongsari.grapher.region.repository;

import com.udongsari.grapher.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findAllBySi(String si);
    List<Region> findAllBySiAndGu(String si, String gu);
    List<Region> findAllBySiAndGuAndDong(String si, String gu, String dong);
    Optional<Region> findBySiAndGuAndDong(String si, String gu, String dong);

}
