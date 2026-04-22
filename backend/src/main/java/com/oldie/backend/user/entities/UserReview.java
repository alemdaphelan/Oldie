package com.oldie.backend.user.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "review_id", nullable = false, updatable = false)
    private UUID reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewee_id", nullable = false)
    private User reviewee;

    @Column(name = "listing_id", nullable = false)
    private UUID listingId;

    @Column(name = "content", nullable = true, length = 500)
    private String content;

    @Column(name = "rate_stars", nullable = false)
    private Integer rateStars;

    @Builder.Default
    @Column(name = "liked_count", nullable = false)
    private Integer likedCount = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
