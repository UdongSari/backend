package com.udongsari.grapher.grapherDetail.service;

import com.udongsari.account.entity.Account;
import com.udongsari.grapher.grapherDetail.dto.GrapherDetailDto;
import com.udongsari.grapher.grapherDetail.dto.GrapherDetailPreviewDto;

import java.util.List;

public interface GrapherDetailService {
    Long createGrapher(Account account, GrapherDetailDto grapherDetailDto);
    GrapherDetailDto showGrapherDetail(Long id);
    List<GrapherDetailDto> readAllGrapher();
    List<GrapherDetailPreviewDto> searchPreviewGrapher(String si, String gu, String dong);
}
