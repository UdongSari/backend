package com.udongsari.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "CLIENT_POST")
public class Client_PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private int price;
    private String request;

    @JsonIgnore
    @ManyToOne(
            targetEntity = com.udongsari.entity.Account.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "USER_ID")
    private Account Account;

    @Builder
    public Client_PostEntity(Long id, String date, int price, String request, com.udongsari.entity.Account account) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.request = request;
    }

}
