package com.udongsari.user.post.dto;

import com.udongsari.grapher.region.dto.RegionDto;
import com.udongsari.grapher.thema.dto.ThemaDto;
import com.udongsari.user.portfolio.dto.UserPortfolioDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPostDto {
    private Long id;
    private Long account_id;
    private String userName;
    private String intro;

    private String startDate;
    private String endDate;
    private int price;

    private List<UserPortfolioDto> portfolioList;
    private List<RegionDto> regionList;
    private List<ThemaDto> themaList;
}
