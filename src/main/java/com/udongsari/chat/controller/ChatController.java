package com.udongsari.chat.controller;

import com.udongsari.chat.dto.ChatPreviewDto;
import com.udongsari.chat.dto.ChatRoomDto;
import com.udongsari.chat.dto.ChatRoomPreviewDto;
import com.udongsari.chat.entity.Chat;
import com.udongsari.chat.service.ChatRoomDBService;
import com.udongsari.chat.service.ChatRoomService;
import com.udongsari.chat.service.ChatService;
import com.udongsari.configure.details.PrincipalDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatRoomDBService chatRoomDBService;
    private final ChatService chatService;

    @GetMapping("/joinRoom/{id}")
    public ChatPreviewDto joinRoom(
            Authentication authentication,
            @PathVariable("id") Long targetId
    ){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        return chatRoomDBService.joinChatRoom(principalDetails.getUser(), targetId);
    }

    @GetMapping("/searchMyRoom")
    public List<ChatRoomPreviewDto> searchMyRoomList(
            Authentication authentication
    ){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        return chatRoomDBService.searchMyChatRoomList(principalDetails.getUser());
    }
}