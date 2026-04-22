package com.oldie.backend.user.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_providers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_provider_id", nullable = false, updatable = false)
    private UUID userProviderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "provider_id", nullable = false, length = 50)
    private String providerId;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}