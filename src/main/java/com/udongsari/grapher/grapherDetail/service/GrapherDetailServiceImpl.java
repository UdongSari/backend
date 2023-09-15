package com.udongsari.grapher.grapherDetail.service;

import com.udongsari.account.entity.Account;
import com.udongsari.account.repository.AccountRepository;
import com.udongsari.exception.grapherNotFoundException;
import com.udongsari.grapher.grapherDetail.dto.GrapherDetailDto;
import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import com.udongsari.grapher.grapherDetail.repository.GrapherDetailRepository;
import com.udongsari.grapher.portfolio.dto.PortfolioDto;
import com.udongsari.grapher.portfolio.entity.Portfolio;
import com.udongsari.grapher.region.dto.RegionDto;
import com.udongsari.grapher.region.entity.Grapher_Region;
import com.udongsari.grapher.region.entity.Region;
import com.udongsari.grapher.thema.dto.ThemaDto;
import com.udongsari.grapher.thema.entity.Grapher_Thema;
import com.udongsari.grapher.thema.entity.Thema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GrapherDetailServiceImpl implements GrapherDetailService {
    private final AccountRepository accountRepository;
    private final GrapherDetailRepository grapherDetailRepository;

    @Override
    public Long createGrapher(Account account, GrapherDetailDto grapherDetailDto) {
        if (account.getGrapherDetail() != null) {
            return null;
        }

        GrapherDetail grapherDetail = GrapherDetail.builder()
                .snsAddress(grapherDetailDto.getSnsAddress())
                .price(grapherDetailDto.getPrice())
                .account(account)
                .build();

        grapherDetailRepository.save(grapherDetail);
        return grapherDetail.getId();
    }

    @Override
    public GrapherDetailDto readGrapher(Long id) {
        Optional<GrapherDetail> grapherDetailOptional = grapherDetailRepository.findById(id);
        if (grapherDetailOptional.isEmpty()) {
            throw new grapherNotFoundException(" * 존재하지 않는 사진작가");
        }

        return grapherDetailOptional.get().toDto();
    }

    @Override
    public List<GrapherDetailDto> readAllGrapher() {
        List<GrapherDetail> grapherDetails = grapherDetailRepository.findAll();
        List<GrapherDetailDto> grapherDetailDtos = new ArrayList<>();

        for (GrapherDetail grapherDetail : grapherDetails) {
            grapherDetailDtos.add(grapherDetail.toDto());
        }

        return grapherDetailDtos;
    }
}
