package com.udongsari.grapher.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioDto {
    private Long id;
    private String imageTitle;
    private String imageDescription;
    private String imagePath;
}
