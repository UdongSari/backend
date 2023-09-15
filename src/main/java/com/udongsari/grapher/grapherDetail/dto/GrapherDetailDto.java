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
public class GrapherDetailDto {
    private Long id;
    private Long account_id;

    private String snsAddress;
    private String price;

    private List<PortfolioDto> portfolios;
    private List<RegionDto> regions;
    private List<ThemaDto> themas;
}
