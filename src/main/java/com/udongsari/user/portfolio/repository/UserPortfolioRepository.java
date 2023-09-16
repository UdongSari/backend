package com.udongsari.user.portfolio.repository;

import com.udongsari.user.portfolio.entity.UserPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPortfolioRepository extends JpaRepository<UserPortfolio, Long> {

    @Query(
            value = "select top 4 * from Themas_portfolio where Thema_id= :Thema_id"
            ,nativeQuery = true
    )
    List<UserPortfolio> findTop4ByUserId(@Param("Thema_id") Long ThemaId);
}
