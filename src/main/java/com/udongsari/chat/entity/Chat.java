package com.udongsari.chat.entity;

import com.udongsari.account.entity.Account;
import com.udongsari.chat.dto.MessageType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="Chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_ID")
    private Long id;

    @Column(name = "MESSAGE_TYPE")
    private MessageType type;

    @Column(name = "ROOM_ID")
    private String roomId;

    @Column(name="MESSAGE")
    private String message;

    @Column(name="TIME")
    private String time;

    @Column(name="SENDER")
    private String sender;

    // Di
    @OneToOne
    @JoinColumn(name="ACCOUNT_ID")
    private Account account;

    @Builder
    public Chat(Long id, MessageType type, String roomId, String message, String time, String sender, Account account) {
        this.id = id;
        this.type = type;
        this.roomId = roomId;
        this.message = message;
        this.time = time;
        this.sender = sender;
        this.account = account;
    }
}
