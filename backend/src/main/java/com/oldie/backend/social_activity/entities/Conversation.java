package com.oldie.backend.social_activity.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "conversations")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {
    @Id
    @Field("conversation_id")
    private String conversationId;

    @Field("user_ids")
    private List<String> userIds;

    @Field("listing_id")
    private String listingId;

    @Field("type")
    private String type; // e.g., "INQUIRY", "NEGOTIATION", "POST_SALE"

    @Field("participant_ids")
    private List<String> participantIds;

    @Field("last_message")
    private String lastMessage;

    @Builder.Default
    @Field("unread_count")
    private Integer unReadCount = 0;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private LocalDateTime updatedAt;

}