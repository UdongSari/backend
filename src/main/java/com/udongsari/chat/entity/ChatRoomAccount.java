package com.udongsari.chat.entity;

import com.udongsari.account.entity.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="ChatRoomAccount")
public class ChatRoomAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHATROOMACCOUNT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ChatRoom.class)
    @JoinColumn(name = "CHATROOM_ID")
    private ChatRoom chatRoom;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Account.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Builder
    public ChatRoomAccount(Long id, ChatRoom chatRoom, Account account) {
        this.id = id;
        this.chatRoom = chatRoom;
        this.account = account;
    }
}
