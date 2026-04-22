package com.oldie.backend.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID userId;
    private String email;
    private String avatarUrl;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String bio;
    private String reputationScore;
    private String totalReviews;
    private String successTrades;
    private String addressDetail;
    private Integer followerCount;
    private Integer followingCount;

    @Builder.Default
    private Boolean isAuth = true;
}