package com.udongsari.chat.repository;

import com.udongsari.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository <ChatRoom, Long> {
}
