package com.oldie.backend.social_activity.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Document(collection = "user_logs")
@CompoundIndexes({
        @CompoundIndex(name = "action_time_idx", def = "{'action_type':1, 'created_at': -1}"),
        @CompoundIndex(name = "user_action_idx", def = "{'user_id': 1, 'action_type': 1, 'created_at': -1}"),
        @CompoundIndex(name = "user_idx", def = "{'user_id': 1, 'created_at' :-1}")
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLog {
    @Id
    private UUID logId;

    @Field("user_id")
    private UUID userId;

    @Field("action_type")
    private String actionType; // e.g., "LOGIN", "LOGOUT", "VIEW_LISTING", "LIKE_LISTING"

    @Field("metadata")
    private Map<String, Object> metadata; // JSON string for additional details

    @Field("device_info")
    private Map<String, Object> deviceInfo;

    @Indexed(expireAfter = "90d")
    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;
}