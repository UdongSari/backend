package com.udongsari.grapher.region.service;

import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import com.udongsari.grapher.region.dto.RegionDto;
import com.udongsari.grapher.region.entity.Grapher_Region;
import com.udongsari.grapher.region.entity.Region;
import com.udongsari.grapher.region.repository.GrapherRegionRepository;
import com.udongsari.grapher.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegionServiceImpl implements RegionService {
    private final GrapherRegionRepository grapherRegionRepository;
    private final RegionRepository regionRepository;

    @Override
    public Long createRegion(GrapherDetail grapherDetail, RegionDto regionDto) {
        Region region = regionRepository.save(Region.builder()
                .si(regionDto.getSi())
                .gu(regionDto.getGu())
                .dong(regionDto.getDong())
                .build());

        grapherRegionRepository.save(Grapher_Region.builder()
                .grapherDetail(grapherDetail)
                .region(region)
                .build());

        return region.getId();
    }
}
