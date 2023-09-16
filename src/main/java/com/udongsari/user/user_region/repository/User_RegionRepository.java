package com.udongsari.user.user_region.repository;

import com.udongsari.user.user_region.entity.User_Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface User_RegionRepository extends JpaRepository<User_Region, Long> {
    @Query(
            value = "select * from Thema_region where region_id = :regionId"
            , nativeQuery = true
    )
    List<User_Region> findAllByRegion(@Param("regionId")Long regionId);

    @Query(
            value = "select * from Thema_region where Thema_id = :ThemaPostId"
            , nativeQuery = true
    )
    List<User_Region> findAllByUserPost(@Param("ThemaPostId") Long ThemaPostlId);
}
