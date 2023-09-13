package com.udongsari.grapher.portfolio.repository;

import com.udongsari.grapher.portfolio.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
