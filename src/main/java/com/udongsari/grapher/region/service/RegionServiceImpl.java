package com.udongsari.grapher.region.service;

import com.udongsari.grapher.grapherDetail.repository.GrapherDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final GrapherDetailRepository grapherDetailRepository;

}
