package com.udongsari.grapher.portfolio.service;

import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import com.udongsari.grapher.portfolio.dto.PortfolioDto;

public interface PortfolioService {
    Long createPortfolio(GrapherDetail grapherDetail, PortfolioDto portfolioDto);
}
