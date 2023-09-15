package com.udongsari.grapher.grapherDetail.entity;

import com.udongsari.account.entity.Account;
import com.udongsari.grapher.grapherDetail.dto.GrapherDetailDto;
import com.udongsari.grapher.portfolio.dto.PortfolioDto;
import com.udongsari.grapher.portfolio.entity.Portfolio;
import com.udongsari.grapher.region.dto.RegionDto;
import com.udongsari.grapher.region.entity.Grapher_Region;
import com.udongsari.grapher.region.entity.Region;
import com.udongsari.grapher.thema.dto.ThemaDto;
import com.udongsari.grapher.thema.entity.Grapher_Thema;
import com.udongsari.grapher.thema.entity.Thema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRAPHER_DETAILS")
public class GrapherDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRAPHER_ID")
    private Long id;

    // Details
    @Column(name = "SNS_ADDRESS")
    private String snsAddress;

    @Column(name = "PRICE")
    private String price;

    @Column(name = "STARS")
    private int stars;

    // Di
    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @OneToMany(mappedBy = "grapherDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Portfolio> portfolios  = new ArrayList<>();

    @OneToMany(mappedBy = "grapherDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grapher_Region> grapherRegions  = new ArrayList<>();

    @OneToMany(mappedBy = "grapherDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grapher_Thema> grapherThemas  = new ArrayList<>();

    @Builder
    public GrapherDetail(Long id, String snsAddress, String price, int stars, Account account, List<Portfolio> portfolios, List<Grapher_Region> grapherRegions, List<Grapher_Thema> grapherThemas) {
        this.id = id;
        this.snsAddress = snsAddress;
        this.price = price;
        this.stars = stars;
        this.account = account;
        this.portfolios = portfolios;
        this.grapherRegions = grapherRegions;
        this.grapherThemas = grapherThemas;
    }

    public GrapherDetailDto toDto() {
        List<Portfolio> portfolios = this.portfolios;
        List<PortfolioDto> portfolioDtos = new ArrayList<>();
        for (Portfolio portfolio : portfolios) {
            portfolioDtos.add(PortfolioDto.builder()
                    .id(portfolio.getId())
                    .imageTitle(portfolio.getImageTitle())
                    .imageDescription(portfolio.getImageDescription())
                    .imagePath(portfolio.getImagePath())
                    .build());
        }

        List<Grapher_Region> grapherRegions = this.grapherRegions;
        List<RegionDto> regionDtos = new ArrayList<>();
        for (Grapher_Region grapherRegion : grapherRegions) {
            Region region = grapherRegion.getRegion();

            regionDtos.add(RegionDto.builder()
                    .id(region.getId())
                    .si(region.getSi())
                    .gu(region.getGu())
                    .dong(region.getDong())
                    .build()
            );

        }

        List<Grapher_Thema> grapherThemas = this.grapherThemas;
        List<ThemaDto> themaDtos = new ArrayList<>();
        for (Grapher_Thema grapherThema : grapherThemas) {
            Thema thema = grapherThema.getThema();

            themaDtos.add(ThemaDto.builder()
                    .id(thema.getId())
                    .themaName(thema.getThemaName())
                    .build()
            );

        }

        return GrapherDetailDto.builder()
                .id(this.id)
                .account_id(this.account.getId())
                .snsAddress(this.snsAddress)
                .price(this.price)
                .stars(this.stars)
                .portfolios(portfolioDtos)
                .regions(regionDtos)
                .themas(themaDtos)
                .build();
    }
}
