package com.udongsari.grapher.grapherDetail.service;

import com.udongsari.account.entity.Account;
import com.udongsari.exception.grapherNotFoundException;
import com.udongsari.grapher.grapherDetail.dto.GrapherDetailDto;
import com.udongsari.grapher.grapherDetail.dto.GrapherDetailPreviewDto;
import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import com.udongsari.grapher.grapherDetail.repository.GrapherDetailRepository;
import com.udongsari.grapher.portfolio.dto.PortfolioDto;
import com.udongsari.grapher.region.dto.RegionDto;
import com.udongsari.grapher.region.entity.Grapher_Region;
import com.udongsari.grapher.region.entity.Region;
import com.udongsari.grapher.region.repository.GrapherRegionRepository;
import com.udongsari.grapher.region.repository.RegionRepository;
import com.udongsari.grapher.thema.dto.ThemaDto;
import com.udongsari.grapher.thema.entity.Grapher_Thema;
import com.udongsari.grapher.thema.entity.Thema;
import com.udongsari.grapher.thema.repository.GrapherThemaRepository;
import com.udongsari.grapher.thema.repository.ThemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GrapherDetailServiceImpl implements GrapherDetailService {
    private final GrapherDetailRepository grapherDetailRepository;

    private final GrapherRegionRepository grapherRegionRepository;
    private final GrapherThemaRepository grapherThemaRepository;

    private final RegionRepository regionRepository;
    private final ThemaRepository themaRepository;

    @Override
    public Long createGrapher(Account account, GrapherDetailDto grapherDetailDto) {
        if (account.getGrapherDetail() != null) {
            return null;
        }

        GrapherDetail grapherDetail = GrapherDetail.builder()
                .snsAddress(grapherDetailDto.getSnsAddress())
                .price(grapherDetailDto.getPrice())
                .stars(grapherDetailDto.getStars())
                .account(account)
                .build();

        grapherDetailRepository.save(grapherDetail);

        List<ThemaDto> themaDtos = grapherDetailDto.getThemas();

        for (ThemaDto themaDto : themaDtos) {
            Optional<Thema> themaOptional = themaRepository.findByThemaName(themaDto.getThemaName());

            if (themaOptional.isEmpty()) {
                Thema thema = themaRepository.save(Thema.builder()
                        .themaName(themaDto.getThemaName())
                        .build());

                grapherThemaRepository.save(Grapher_Thema.builder()
                        .grapherDetail(grapherDetail)
                        .thema(thema)
                        .build());
            } else {
                grapherThemaRepository.save(Grapher_Thema.builder()
                        .grapherDetail(grapherDetail)
                        .thema(themaOptional.get())
                        .build());
            }
        }

        List<RegionDto> regionDtos = grapherDetailDto.getRegions();

        for (RegionDto regionDto : regionDtos) {
            Optional<Region> regionOptional = regionRepository.findBySiAndGuAndDong(regionDto.getSi(), regionDto.getGu(), regionDto.getDong());

            if (regionOptional.isEmpty()) {
                Region region = regionRepository.save(Region.builder()
                        .si(regionDto.getSi())
                        .gu(regionDto.getGu())
                        .dong(regionDto.getDong())
                        .build());

                grapherRegionRepository.save(Grapher_Region.builder()
                        .grapherDetail(grapherDetail)
                        .region(region)
                        .build());
            } else {
                grapherRegionRepository.save(Grapher_Region.builder()
                        .grapherDetail(grapherDetail)
                        .region(regionOptional.get())
                        .build());
            }
        }

        return grapherDetail.getId();
    }

    @Override
    public GrapherDetailDto showGrapherDetail(Long id) {
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

    @Override
    public List<GrapherDetailPreviewDto> searchPreviewGrapher(String si, String gu, String dong){
        List<GrapherDetailPreviewDto> grapherDetailPreviewDtos = new ArrayList<>();

        List<RegionDto> regionList = new ArrayList<>();
        List<PortfolioDto> portfolioList = new ArrayList<>();
        List<ThemaDto> themaList = new ArrayList<>();
        List<Region> regions = new ArrayList<>();

         if (si != null && gu != null && dong != null) { // si, gu, dong 있을 때
            regions = regionRepository.findAllBySiAndGuAndDong(si, gu, dong);
        } else if (si != null && gu != null) { // si, gu 있을 때
            regions = regionRepository.findAllBySiAndGu(si, gu);
        } else if (si != null) { // si 있을 때
            regions = regionRepository.findAllBySi(si);
        }

        for (Region region : regions) {
            List<Grapher_Region> grapherRegions = region.getGrapherRegions();

            for (Grapher_Region grapherRegion : grapherRegions) {
                GrapherDetail grapherDetail = grapherRegion.getGrapherDetail();

                grapherDetailPreviewDtos.add(GrapherDetailPreviewDto.builder()
                        .grapherName(grapherDetail.getAccount().getName())
                        .stars(grapherDetail.getStars())
                        .regionList(regionList)
                        .themaList(themaList)
                        .portfolioList(portfolioList)
                        .build());
            }
        }

        return grapherDetailPreviewDtos;
    }
}
