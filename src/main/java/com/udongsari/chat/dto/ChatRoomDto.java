package com.udongsari.chat.dto;

import com.udongsari.chat.service.ChatRoomService;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ChatRoomDto {
    private String roomId;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoomDto(String roomId){
        this.roomId = roomId;
    }

    public void handleAction(WebSocketSession session, ChatDto message, ChatRoomService service) {
        if (message.getType().equals(MessageType.ENTER)) {
            message.setMessage(message.getSender() + " 님이 입장하셨습니다");

            sessions.add(session);
        } else if (message.getType().equals(MessageType.TALK)) {
            message.setMessage(message.getMessage());
        }

        sendMessage(message, service);
    }

    public <T> void sendMessage(T message, ChatRoomService service) {
        sessions.parallelStream().forEach(session -> service.sendMessage(session, message));
    }
}
