package com.udongsari.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udongsari.dto.AccountDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "USER_ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    // Auth
    @Column(name = "EMAIL")
    private String username; // email
    @Column(name = "PW")
    private String password;

    // Details
    @Column(name = "NAME")
    private String name;
    @Column(name = "AGE")
    private int age;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "ACTIVE_RANGE")
    private String activeRange;

    // roles
    @Column(name = "ROLE")
    private String roles;

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @JsonIgnore
    @OneToMany(
            targetEntity = User_Region.class,
            mappedBy = "Account",
            fetch = FetchType.LAZY
    )
    private List<User_Region> user_regionList;

    @JsonIgnore
    @OneToMany(
            targetEntity = User_Thema.class,
            mappedBy = "Account",
            fetch = FetchType.LAZY
    )
    private List<User_Thema> user_themaList;

    @JsonIgnore
    @OneToMany(
            targetEntity = User_Chatting.class,
            mappedBy = "Account",
            fetch = FetchType.LAZY
    )
    private List<User_Chatting> user_chattingList;

    @JsonIgnore
    @OneToMany(
            targetEntity = Client_PostEntity.class,
            mappedBy = "Account",
            fetch = FetchType.LAZY
    )
    private List<Client_PostEntity> client_postEntityList;

    @JsonIgnore
    @OneToOne(
            targetEntity = PhotoGrapher_PostEntity.class,
            mappedBy = "Account",
            fetch = FetchType.LAZY
    )
    private PhotoGrapher_PostEntity photoGrapher_postEntity;




    @Builder
    public Account(Long id, String username, String password, String name, int age, String phoneNumber, String activeRange, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.activeRange = activeRange;
        this.roles = roles;
    }

    public AccountDto toDto() {
        return AccountDto.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .name(this.name)
                .age(this.age)
                .phoneNumber(phoneNumber)
                .activeRange(activeRange)
                .roles(this.roles)
                .build();
    }
}
