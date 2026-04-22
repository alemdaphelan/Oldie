package com.oldie.backend.social_activity.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "conversations")
@CompoundIndexes({
        @CompoundIndex(name = "userId_idx", def = "{'participant_ids': 1,'updated_at: -1'}"),
        @CompoundIndex(name = "listing_participant_idx", def = "{'listing_id': 1, 'participant_ids' : 1}")
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {
    @Id
    private UUID conversationId;

    @Field("listing_id")
    private UUID listingId;

    @Field("type")
    private String type; // e.g., "INQUIRY", "NEGOTIATION", "POST_SALE"

    @Field("participant_ids")
    private List<UUID> participantIds;

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