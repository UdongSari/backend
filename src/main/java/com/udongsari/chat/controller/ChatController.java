package com.udongsari.chat.controller;

import com.udongsari.chat.dto.ChatRoomDto;
import com.udongsari.chat.entity.Chat;
import com.udongsari.chat.service.ChatRoomService;
import com.udongsari.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatService chatService;

    @PostMapping("/createRoom")
    public ChatRoomDto createRoom(){
        return chatRoomService.createRoom();
    }

    @GetMapping("/AllRoom")
    public List<ChatRoomDto> findAllRooms(){
        return chatRoomService.findAllRoom();
    }

    @GetMapping()
    public List<Chat> findAllChat(){
        return chatService.findAllChat();
    }

    @GetMapping("/{roomId}")
    public List<Chat> findAllByRoomChat(@PathVariable("roomId") String roomId){
        return chatService.findAllByRoomId(roomId);
    }
}