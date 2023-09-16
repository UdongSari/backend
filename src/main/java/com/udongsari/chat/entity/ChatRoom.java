package com.udongsari.chat.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="ChatRoom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHATROOM_ID")
    private Long id;

    private String roomId;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatRoomAccount> chatRoomAccounts;

    @Builder
    public ChatRoom(Long id, String roomId, List<ChatRoomAccount> chatRoomAccounts) {
        this.id = id;
        this.roomId = roomId;
        this.chatRoomAccounts = chatRoomAccounts;
    }
}
