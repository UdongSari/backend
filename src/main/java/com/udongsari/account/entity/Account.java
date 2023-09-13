package com.udongsari.account.entity;

import com.udongsari.account.dto.AccountDto;
import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
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
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    // Auth
    @Column(name = "EMAIL")
    private String username; // email
    @Column(name = "PW")
    private String password;

    // GrapherDetail
    @Column(name = "NAME")
    private String name;
    @Column(name = "AGE")
    private int age;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    // Roles
    @Column(name = "ROLE")
    private String roles;

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    // Di
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private GrapherDetail grapherDetail;

    // Builder
    @Builder
    public Account(Long id, String username, String password, String name, int age, String phoneNumber, String roles, GrapherDetail grapherDetail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.grapherDetail = grapherDetail;
    }

    // toDto
    public AccountDto toDto() {
        return AccountDto.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .name(this.name)
                .age(this.age)
                .phoneNumber(phoneNumber)
                .roles(this.roles)
                .build();
    }
}
