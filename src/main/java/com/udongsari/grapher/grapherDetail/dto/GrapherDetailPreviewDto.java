package com.udongsari.grapher.grapherDetail.dto;

import com.udongsari.grapher.portfolio.dto.PortfolioDto;
import com.udongsari.grapher.region.dto.RegionDto;
import com.udongsari.grapher.thema.dto.ThemaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrapherDetailPreviewDto {

    private Long id;

    private String grapherName;
    private String intro;
    private int stars;

    private List<PortfolioDto> portfolioList;
    private List<RegionDto> regionList;
    private List<ThemaDto> themaList;
}