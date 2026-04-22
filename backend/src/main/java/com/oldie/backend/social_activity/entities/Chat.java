package com.oldie.backend.social_activity.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "chats")
@CompoundIndex(name = "idx_conv_time", def = "{'conversation_id': 1, 'created_at': -1}")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    private UUID chatId;

    @Field("conversation_id")
    private UUID conversationId;

    @Field("sender_id")
    private String senderId;

    @Field("message")
    private String message;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private LocalDateTime updatedAt;
}