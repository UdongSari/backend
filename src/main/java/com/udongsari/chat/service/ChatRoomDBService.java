package com.udongsari.chat.service;

import com.udongsari.account.entity.Account;
import com.udongsari.account.repository.AccountRepository;
import com.udongsari.chat.dto.ChatPreviewDto;
import com.udongsari.chat.dto.ChatRoomDto;
import com.udongsari.chat.dto.ChatRoomPreviewDto;
import com.udongsari.chat.entity.Chat;
import com.udongsari.chat.entity.ChatRoom;
import com.udongsari.chat.entity.ChatRoomAccount;
import com.udongsari.chat.repository.ChatRepository;
import com.udongsari.chat.repository.ChatRoomAccountRepository;
import com.udongsari.chat.repository.ChatRoomRepository;
import com.udongsari.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomDBService {
    private final AccountRepository accountRepository;
    private final ChatRoomAccountRepository chatRoomAccountRepository; // proxy
    private final ChatRoomRepository chatRoomRepository;

    private final ChatRepository chatRepository;
    private final ChatRoomService chatRoomService;

    @Transactional
    public ChatPreviewDto joinChatRoom(Account myAccount, Long targetUserId) {
        Optional<Account> targetAccountOptional = accountRepository.findById(targetUserId);

        if (targetAccountOptional.isEmpty()) {
            throw new AccountNotFoundException(" * Target Account 찾을 수 없음");
        }

        Account targetAccount = targetAccountOptional.get();

        List<ChatRoomAccount> myChatRoomAccountList = myAccount.getChatRoomAccount();
        List<ChatRoomAccount> targetChatRoomAccountList = targetAccount.getChatRoomAccount();
        String sameRoomId = null;

        for (ChatRoomAccount mychatRoomAccount : myChatRoomAccountList) {
            for (ChatRoomAccount targetchatRoomAccount : targetChatRoomAccountList) {
                if (mychatRoomAccount.getChatRoom().getRoomId()
                        .equals(targetchatRoomAccount.getChatRoom().getRoomId())) {
                    sameRoomId = mychatRoomAccount.getChatRoom().getRoomId();
                    break;
                }
            }
        }

        if (sameRoomId == null) { // 최초 채팅방 입장 시 채팅방 생성
            ChatRoomDto chatRoomDto = chatRoomService.createRoom();

            ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.builder()
                    .roomId(chatRoomDto.getRoomId()).build());

            chatRoomAccountRepository.save(ChatRoomAccount.builder()
                    .chatRoom(chatRoom)
                    .account(myAccount)
                    .build());

            chatRoomAccountRepository.save(ChatRoomAccount.builder()
                    .chatRoom(chatRoom)
                    .account(targetAccount)
                    .build());

            // 룸id만
            return ChatPreviewDto.builder()
                    .roomId(chatRoomDto.getRoomId())
                    .build();
        }

        // 룸id, 채팅내역
        List<Chat> chatList = chatRepository.findAllByRoomId(sameRoomId, Sort.by(Sort.Direction.ASC, "id"));

        return ChatPreviewDto.builder()
                .roomId(sameRoomId)
                .chatList(chatList)
                .build();
    }

    @Transactional
    public List<ChatRoomPreviewDto> searchMyChatRoomList(Account account) {
        List<ChatRoomPreviewDto> chatRoomPreviewDtos = new ArrayList<>();
        List<ChatRoomAccount> chatRoomAccounts = account.getChatRoomAccount();

        for (ChatRoomAccount chatRoomAccount : chatRoomAccounts) {
            List<ChatRoomAccount> targetChatRoom = chatRoomAccountRepository.findAllByChatRoom(chatRoomAccount.getChatRoom());

            for (ChatRoomAccount cra : targetChatRoom) {
                if (!cra.getAccount().getUsername().equals(account.getUsername())) {
                    ChatRoomPreviewDto chatRoomPreviewDto = ChatRoomPreviewDto.builder()
                            .chatRoomId(cra.getChatRoom().getRoomId())
                            .targetId(cra.getAccount().getId())
                            .targetName(cra.getAccount().getName())
                            .build();

                    chatRoomPreviewDtos.add(chatRoomPreviewDto);
                }
            }
        }

        return chatRoomPreviewDtos;

    }
}
