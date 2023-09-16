package com.udongsari.chat.service;

import com.udongsari.chat.dto.ChatDto;
import com.udongsari.chat.entity.Chat;
import com.udongsari.chat.repository.ChatRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public void createChat(ChatDto message){
        chatRepository.save(Chat.builder()
                .type(message.getType())
                .roomId(message.getRoomId())
                .message(message.getMessage())
                .time(message.getTime())
                .sender(message.getSender()).build());
    }

    public Chat findChatById(Long id){
        return chatRepository.findById(id).get();
    }

    public List<Chat> findAllChat(){
        return chatRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Chat> findAllByRoomId(String roomId){

        return chatRepository.findAllByRoomId(roomId, Sort.by(Sort.Direction.ASC, "id"));
    }

}
