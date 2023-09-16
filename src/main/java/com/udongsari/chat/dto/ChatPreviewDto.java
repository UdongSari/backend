package com.udongsari.chat.dto;

import com.udongsari.chat.entity.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatPreviewDto {
    private String roomId;
    private List<Chat> chatList;
}
