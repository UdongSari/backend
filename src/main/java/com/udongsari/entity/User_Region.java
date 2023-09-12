package com.udongsari.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User_Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(
            targetEntity = Account.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "USER_ID")
    private Account Account;

    @JsonIgnore
    @ManyToOne(
            targetEntity = RegionEntity.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "REGION_ID")
    private RegionEntity region ;

    @Builder
    public User_Region(Long id, com.udongsari.entity.Account account, RegionEntity region) {
        this.id = id;
        Account = account;
        this.region = region;
    }
}
