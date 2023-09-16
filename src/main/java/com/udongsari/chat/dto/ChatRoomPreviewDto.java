package com.udongsari.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomPreviewDto {
    private String chatRoomId;
    private Long targetId;
    private String targetName;
}
