package com.udongsari.user.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPortfolioDto {
    private Long id;
    private String imageTitle;
    private String imagePath;
    private Long post_id;
}
