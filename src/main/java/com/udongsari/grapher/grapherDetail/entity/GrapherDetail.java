package com.udongsari.grapher.grapherDetail.entity;

import com.udongsari.account.entity.Account;
import com.udongsari.grapher.portfolio.entity.Portfolio;
import com.udongsari.grapher.region.entity.Grapher_Region;
import com.udongsari.grapher.thema.entity.Grapher_Thema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    // Di
    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @OneToMany(mappedBy = "grapherDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Portfolio> portfolios;

    @OneToMany(mappedBy = "grapherDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grapher_Region> grapherRegions;

    @OneToMany(mappedBy = "grapherDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grapher_Thema> grapherThemas;

}
