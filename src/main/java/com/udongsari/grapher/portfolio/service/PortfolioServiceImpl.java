package com.udongsari.grapher.portfolio.service;

import com.udongsari.grapher.grapherDetail.repository.GrapherDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    private final GrapherDetailRepository grapherDetailRepository;

}
