package com.udongsari.grapher.region.service;

import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import com.udongsari.grapher.region.dto.RegionDto;

public interface RegionService {
    Long createRegion(GrapherDetail grapherDetail, RegionDto regionDto);
}
