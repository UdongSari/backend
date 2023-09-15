package com.udongsari.account.dto;

import com.udongsari.account.entity.Account;
import com.udongsari.grapher.grapherDetail.entity.GrapherDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Long id;

    private String username;
    private String password;

    private String name;
    private int age;
    private String phoneNumber;

    private String roles;
    private GrapherDetail grapherDetail;

    public Account toEntity() {
        return Account.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .name(this.name)
                .age(this.age)
                .phoneNumber(phoneNumber)
                .roles(this.roles)
                .grapherDetail(grapherDetail)
                .build();
    }
}
