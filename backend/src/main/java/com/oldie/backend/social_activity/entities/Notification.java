package com.oldie.backend.social_activity.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Document(collection = "notifications")
@CompoundIndexes({
        @CompoundIndex(name = "user_time_idx", def = "{'user_id': 1, 'created_at': -1}"),
        @CompoundIndex(name = "unread_idx", def = "{'user_id' : 1, 'isRead' : 1}")
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private UUID notificationId;

    @Field("user_id")
    private UUID userId;

    @Field("type")
    private String type; // e.g., "NEW_MESSAGE", "ORDER_UPDATE", "LIST

    @Field("title")
    private String title;

    @Field("message")
    private String message;

    @Field("metadata")
    private Map<String, Object> metadata;

    @Builder.Default
    @Field("is_read")
    private Boolean isRead = false;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;
}