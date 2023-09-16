package com.udongsari.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udongsari.chat.dto.ChatDto;
import com.udongsari.chat.dto.ChatRoomDto;
import com.udongsari.chat.service.ChatRoomService;
import com.udongsari.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper;

    private final ChatRoomService chatRoomService;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);

        ChatDto chatMessage = mapper.readValue(payload, ChatDto.class);
        log.info("session {}", chatMessage.toString());

        ChatRoomDto room = chatRoomService.findRoomById(chatMessage.getRoomId());
        log.info("room {}", room.toString());

        room.handleAction(session, chatMessage, chatRoomService);

        chatService.createChat(chatMessage);
    }
}
