package com.udongsari.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User_Chatting {
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
            targetEntity = ChattingEntity.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "CHATTING_ID")
    private ChattingEntity chatting ;

    @Builder
    public User_Chatting(Long id, com.udongsari.entity.Account account, ChattingEntity chatting) {
        this.id = id;
        Account = account;
        this.chatting = chatting;
    }
}
