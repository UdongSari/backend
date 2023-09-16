package com.udongsari.chat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatDto {
    private MessageType type;
    private String roomId;
    private String message;
    private String sender;
    private String time;

    private Long userId;

}
