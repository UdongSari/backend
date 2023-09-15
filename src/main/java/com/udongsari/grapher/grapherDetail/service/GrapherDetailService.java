package com.udongsari.grapher.grapherDetail.service;

import com.udongsari.account.entity.Account;
import com.udongsari.grapher.grapherDetail.dto.GrapherDetailDto;

import java.util.List;

public interface GrapherDetailService {
    Long createGrapher(Account account, GrapherDetailDto grapherDetailDto);
    GrapherDetailDto readGrapher(Long id);
    List<GrapherDetailDto> readAllGrapher();
}
