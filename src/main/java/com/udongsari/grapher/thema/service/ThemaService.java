package com.udongsari.grapher.thema.service;

import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import com.udongsari.grapher.thema.dto.ThemaDto;

public interface ThemaService {
    Long createThema(GrapherDetail grapherDetail, ThemaDto themaDto);
}
