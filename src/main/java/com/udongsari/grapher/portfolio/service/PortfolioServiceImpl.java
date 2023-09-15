package com.udongsari.grapher.portfolio.service;

import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import com.udongsari.grapher.portfolio.dto.PortfolioDto;
import com.udongsari.grapher.portfolio.entity.Portfolio;
import com.udongsari.grapher.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository portfolioRepository;

    @Override
    public Long createPortfolio(GrapherDetail grapherDetail, PortfolioDto portfolioDto) {
        Portfolio portfolio = portfolioRepository.save(Portfolio.builder()
                .imageTitle(portfolioDto.getImageTitle())
                .imagePath(portfolioDto.getImagePath())
                .grapherDetail(grapherDetail)
                .build());

        return portfolio.getId();
    }
}
