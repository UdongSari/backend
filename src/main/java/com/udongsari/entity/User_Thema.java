package com.udongsari.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User_Thema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(
            targetEntity = com.udongsari.entity.Account.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "USER_ID")
    private Account Account;

    @JsonIgnore
    @ManyToOne(
            targetEntity = ThemaEntity.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "THEMA_ID")
    private ThemaEntity thema ;

    @Builder
    public User_Thema(Long id, com.udongsari.entity.Account account, ThemaEntity thema) {
        this.id = id;
        Account = account;
        this.thema = thema;
    }
}
