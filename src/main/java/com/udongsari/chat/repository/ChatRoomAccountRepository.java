package com.udongsari.chat.repository;

import com.udongsari.chat.entity.ChatRoom;
import com.udongsari.chat.entity.ChatRoomAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomAccountRepository extends JpaRepository <ChatRoomAccount, Long> {
    List<ChatRoomAccount> findAllByChatRoom(ChatRoom chatRoom);
}
