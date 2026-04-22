package com.oldie.backend.user.entities;

import jakarta.persistence.*;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {
    @Id
    @Column(name = "user_profile_id", nullable = false, updatable = false)
    private UUID userProfileId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "avatar_url", nullable = true)
    private String avatarUrl;

    @Column(name = "bio", nullable = true, length = 500)
    private String bio;

    @Builder.Default
    @Column(name = "reputation_score", nullable = false)
    private Integer reputationScore = 0;

    @Builder.Default
    @Column(name = "total_reviews", nullable = false)
    private Integer totalReviews = 0;

    @Builder.Default
    @Column(name = "success_trades", nullable = false)
    private Integer successTrades = 0;

    @Column(name = "address_detail", nullable = true)
    private String addressDetail;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder.Default
    @Column(name = "follower_count", nullable = false)
    private Integer followerCount = 0;

    @Builder.Default
    @Column(name = "following_count", nullable = false)
    private Integer followingCount = 0;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

}
