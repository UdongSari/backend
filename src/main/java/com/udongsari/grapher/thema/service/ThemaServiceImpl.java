package com.udongsari.grapher.thema.service;

import com.udongsari.grapher.grapherDetail.repository.GrapherDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemaServiceImpl implements ThemaService {
    private final GrapherDetailRepository grapherDetailRepository;

}
