package com.udongsari.grapher.grapherDetail.service;

import com.udongsari.grapher.grapherDetail.repository.GrapherDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrapherDetailServiceImpl implements GrapherDetailService {
    private final GrapherDetailRepository grapherDetailRepository;

}
