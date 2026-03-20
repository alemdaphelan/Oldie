package com.oldie.backend.social_activity.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "user_logs")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class userLog {
    @Id
    @Field("log_id")
    private String logId;

    @Field("user_id")
    private String userId;

    @Field("action_type")
    private String actionType; // e.g., "LOGIN", "LOGOUT", "VIEW_LISTING", "LIKE_LISTING"

    @Field("metadata")
    private Map<String, Object> metadata; // JSON string for additional details

    @Field("device_info")
    private Map<String, Object> deviceInfo;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;
}