package com.udongsari.grapher.thema.service;

import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import com.udongsari.grapher.thema.dto.ThemaDto;
import com.udongsari.grapher.thema.entity.Grapher_Thema;
import com.udongsari.grapher.thema.entity.Thema;
import com.udongsari.grapher.thema.repository.GrapherThemaRepository;
import com.udongsari.grapher.thema.repository.ThemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ThemaServiceImpl implements ThemaService {
    private final GrapherThemaRepository grapherThemaRepository;
    private final ThemaRepository themaRepository;

    @Override
    public Long createThema(GrapherDetail grapherDetail, ThemaDto themaDto) {
        Thema thema = themaRepository.save(Thema.builder()
                .themaName(themaDto.getThemaName())
                .build());

        grapherThemaRepository.save(Grapher_Thema.builder()
                .grapherDetail(grapherDetail)
                .thema(thema)
                .build());

        return thema.getId();
    }
}
