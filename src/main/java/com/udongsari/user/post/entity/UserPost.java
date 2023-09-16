package com.udongsari.user.post.entity;

import com.udongsari.account.entity.Account;
import com.udongsari.grapher.region.dto.RegionDto;
import com.udongsari.grapher.region.entity.Region;
import com.udongsari.grapher.thema.dto.ThemaDto;
import com.udongsari.grapher.thema.entity.Thema;
import com.udongsari.user.portfolio.entity.UserPortfolio;
import com.udongsari.user.portfolio.dto.UserPortfolioDto;
import com.udongsari.user.post.dto.UserPostDto;
import com.udongsari.user.user_region.entity.User_Region;
import com.udongsari.user.user_thema.entity.UserThema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CLIENT_POST")
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    // Details
    @Column(name = "PRICE")
    private int price;
    @Column(name = "INTRO")
    private String intro;

    @Column(name = "START_DATE")
    private String startDate;

    @Column(name = "END_DATE")
    private String endDate;

    // Di
    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @OneToMany(mappedBy = "userPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserPortfolio> portfolioList;

    @OneToMany(mappedBy = "userPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User_Region> ThemaRegionList;

    @OneToMany(mappedBy = "userPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserThema> ThemaThemaList;

    @Builder
    public UserPost(Long id, int price, String intro, String startDate, String endDate, Account account, List<UserPortfolio> portfolioList, List<User_Region> ThemaRegionList, List<UserThema> ThemaThemaList) {
        this.id = id;
        this.price = price;
        this.intro = intro;
        this.startDate = startDate;
        this.endDate = endDate;
        this.account = account;
        this.portfolioList = portfolioList;
        this.ThemaRegionList = ThemaRegionList;
        this.ThemaThemaList = ThemaThemaList;
    }

    public UserPostDto toDto() {
        return UserPostDto.builder()
                .id(this.id)
                .account_id(this.account.getId())
                .intro(this.intro)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .price(this.price)
                .portfolioList(getPortfolioDtoList())
                .regionList(getRegionDtoList())
                .themaList(getThemaDtoList())
                .build();
    }

    public List<UserPortfolioDto> getPortfolioDtoList() {
        List<UserPortfolio> portfolios = this.portfolioList;
        List<UserPortfolioDto> portfolioDtos = new ArrayList<>();

        for (UserPortfolio portfolio : portfolios) {
            portfolioDtos.add(UserPortfolioDto.builder()
                    .id(portfolio.getId())
                    .imageTitle(portfolio.getImageTitle())
                    .imagePath(portfolio.getImagePath())
                    .build());
        }

        return portfolioDtos;
    }

    public List<RegionDto> getRegionDtoList() {
        List<User_Region> grapherRegions = this.ThemaRegionList;
        List<RegionDto> regionDtos = new ArrayList<>();
        for (User_Region user_region : grapherRegions) {
            Region region = user_region.getRegion();

            regionDtos.add(RegionDto.builder()
                    .id(region.getId())
                    .si(region.getSi())
                    .gu(region.getGu())
                    .dong(region.getDong())
                    .build()
            );

        }

        return regionDtos;
    }

    public List<ThemaDto> getThemaDtoList() {
        List<UserThema> grapherThemas = this.ThemaThemaList;
        List<ThemaDto> themaDtos = new ArrayList<>();
        for (UserThema user_thema : grapherThemas) {
            Thema thema = user_thema.getThema();

            themaDtos.add(ThemaDto.builder()
                    .id(thema.getId())
                    .themaName(thema.getThemaName())
                    .build());
        }

        return themaDtos;
    }

}