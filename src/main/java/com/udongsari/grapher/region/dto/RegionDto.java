package com.udongsari.grapher.region.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegionDto {
    private Long id;
    private String si;
    private String gu;
    private String dong;
}
