package com.oldie.backend.social_activity.entities;

import com.oldie.backend.user.entities.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "follows")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "follow_id", nullable = false, updatable = false)
    private UUID followId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    private User following;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}